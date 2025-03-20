package ctn.tree_miner.common.blocks;

import com.mojang.serialization.MapCodec;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import net.minecraft.core.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.commons.lang3.function.Suppliers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

import static ctn.tree_miner.ModUtil.isMainHandItemEnchantmentLevel;
import static ctn.tree_miner.create.TreeMinerBlocks.STAGE_3;

/**
 * 矿脉树叶
 */
public class LodeLeavesBlock extends LeavesBlock {
    public static final MapCodec<LodeLeavesBlock> CODEC = simpleCodec(LodeLeavesBlock::new);
    private static final Logger log = LoggerFactory.getLogger(LodeLeavesBlock.class);
    private final Supplier<Item> fruit;
    private final Supplier<BlockItem> sapling;

    @Override
    public MapCodec<? extends LodeLeavesBlock> codec() {
        return CODEC;
    }

    protected LodeLeavesBlock(Properties properties) {
        super(properties);
        this.fruit = Suppliers.nul();
        this.sapling = Suppliers.nul();
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    public LodeLeavesBlock(Properties properties, Supplier<Item> fruit, Supplier<BlockItem> sapling) {
        super(properties);
        this.fruit = fruit;
        this.sapling = sapling;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        Entity entity = params.getOptionalParameter(LootContextParams.THIS_ENTITY);
        List<ItemStack> itemList = new ArrayList<>();
        int referenceRatio = 0;
        int referenceQuantity = 0;
        if (entity instanceof Player player) {
            // 判断是否有精准采集
            if (isMainHandItemEnchantmentLevel(player, Enchantments.SILK_TOUCH) >= 1) {
                itemList.add(this.asItem().getDefaultInstance());
                return itemList;
            }

            // 判断是否有时运
            int fortuneLevel = isMainHandItemEnchantmentLevel(player, Enchantments.FORTUNE);
            if (fortuneLevel != 0) {
                referenceQuantity += fortuneLevel;
                referenceRatio += fortuneLevel * 10;
            }

        }

        RandomSource random = params.getLevel().getRandom();
        if (random.nextInt(100) <= 9 + referenceRatio) {
            itemList.add(sapling.get().getDefaultInstance());
        }
        if (state.getValue(STAGE_3) == 3) {
            ItemStack item = fruit.get().getDefaultInstance();
            item.setCount(random.nextInt(1 + referenceQuantity, 3 + referenceQuantity));
            itemList.add(item);
        }

        // 当有数据驱动掉落物时或物品为空时执行原版掉落物
        if (itemList.isEmpty() || drops.isEmpty()) {
            return super.getDrops(state, params);
        }
        return itemList;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(STAGE_3) != 3 || state.getValue(PERSISTENT)) {
            return InteractionResult.FAIL;
        }

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        var fruitStack = this.fruit.get().getDefaultInstance();
        fruitStack.setCount(level.getRandom().nextInt(1,5));
        if (!player.addItem(fruitStack)) {
            level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY() - 1, pos.getZ() + 0.5, fruitStack));
        }
        level.setBlock(pos, state.setValue(STAGE_3, 0), 3);

        return InteractionResult.SUCCESS;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE_3);
    }

    @Override
    protected void randomTick(BlockState state,  ServerLevel level,  BlockPos pos,  RandomSource random) {
        if (state.getValue(DISTANCE) == 7) {
            level.setBlock(pos, state.setValue(STAGE_3, 0), 3);
        } else if (state.getValue(STAGE_3) != 3) {
            level.setBlock(pos, state.cycle(STAGE_3), 3);
        }

        super.randomTick(state, level, pos, random);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader leve, ScheduledTickAccess access, BlockPos pos, Direction direction, BlockPos adjoinPos, BlockState adjoinState, RandomSource source) {
        if (state.getValue(WATERLOGGED)) {
            access.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(leve));
        }
        int i = getDistanceAt(adjoinState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            access.scheduleTick(pos, this, 1);
        }
        return state;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockstate = this.defaultBlockState()
                .setValue(PERSISTENT, Boolean.TRUE)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        return updateDistance(blockstate, context.getLevel(), context.getClickedPos());
    }

    public BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            BlockState neighbor = level.getBlockState(blockpos$mutableblockpos);
            i = Math.min(i, getDistanceAt(neighbor) + 1);
            if (i == 1) {
                break;
            }
        }
        return state.setValue(DISTANCE, i);
    }

    public int getDistanceAt(BlockState neighbor) {
        return getOptionalDistanceAdjacentBlocks(neighbor).orElse(7);
    }

    public OptionalInt getOptionalDistanceAdjacentBlocks(BlockState state) {
        if (state.is(getLodeLog())) {
            return OptionalInt.of(0);
        } else if (state.is(getLodeLeaves())){
            return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
        }
        return OptionalInt.empty();
    }

    public TagKey<Block> getLodeLeaves() {
        return TreeMinerBlockTags.BlockTags.LODE_LEAVES;
    }

    public TagKey<Block> getLodeLog() {
        return TreeMinerBlockTags.BlockTags.LODE_LOG;
    }
}
