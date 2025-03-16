package ctn.tree_miner.server.blocks;

import com.mojang.serialization.MapCodec;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.BlockHitResult;
import org.apache.commons.lang3.function.Suppliers;

import java.util.OptionalInt;
import java.util.function.Supplier;

import static ctn.tree_miner.create.TreeMinerBlocks.STAGE_3;

/**
 * 矿脉树叶
 */
public class LodeLeavesBlock extends LeavesBlock {
    public static final MapCodec<LodeLeavesBlock> CODEC = simpleCodec(LodeLeavesBlock::new);
    private final Supplier<Item> fruit;

    @Override
    public MapCodec<? extends LodeLeavesBlock> codec() {
        return CODEC;
    }

    protected LodeLeavesBlock(Properties properties) {
        super(properties);
        this.fruit = Suppliers.nul();
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    public LodeLeavesBlock(Properties properties, Supplier<Item> fruit) {
        super(properties);
        this.fruit = fruit;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        int life = state.getValue(STAGE_3);
        if (life != 3) {
            return InteractionResult.FAIL;
        }

        var fruitStack = this.fruit.get().getDefaultInstance();
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
                .setValue(PERSISTENT, Boolean.valueOf(true))
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
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
        return TreeMinerBlockTags.Tags.LODE_LEAVES;
    }

    public TagKey<Block> getLodeLog() {
        return TreeMinerBlockTags.Tags.LODE_LOG;
    }
}
