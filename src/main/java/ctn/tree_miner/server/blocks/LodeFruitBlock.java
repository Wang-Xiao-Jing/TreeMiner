package ctn.tree_miner.server.blocks;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static ctn.tree_miner.create.TreeMinerBlocks.*;
import static net.minecraft.world.level.block.LeavesBlock.*;

/** 矿果方块模版 */
public class LodeFruitBlock extends Block /*implements BucketPickup*/{
    protected static final VoxelShape SHAPE = Block.box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0);
    protected static final VoxelShape SHAPE_MATURE = Block.box(3.0, 6.0, 3.0, 13.0, 16.0, 13.0);
//    protected static ItemStack itemFruitStack = new ItemStack(Blocks.AIR);
    public LodeFruitBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(STAGE_3) == 3 ? SHAPE_MATURE : SHAPE;
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource randomSource) {
//        int age = state.getValue(STAGE_3);
        if (state.getValue(STAGE_3) != 3) {
            level.setBlockAndUpdate(pos, state.cycle(STAGE_3));
        }
        if (state.getValue(STAGE_3) == 3) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    public static BlockState createNewFruitMature() {
        return createNewFruitMature(0);
    }

    public static BlockState createNewFruitMature(int age) {
        return TreeMinerBlocks.LODE_FRUIT_COAL.get().defaultBlockState().setValue(STAGE_3, age);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE_3);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState defaultBlockState = super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        if (!canSurvive(state, level, pos)) {
            if (state.getValue(STAGE_3) == 3 && level instanceof Level serverLevel) {
                serverLevel.removeBlock(pos, false);
            }
            return Blocks.AIR.defaultBlockState();
        }
        return defaultBlockState;
    }


    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos){
        if (level.getBlockState(pos.above()).getBlock() instanceof LodeLeavesBlock) {
            BlockState aboveBlockState = level.getBlockState(pos.above());
            return aboveBlockState.getValue(DISTANCE) != 7 && !aboveBlockState.getValue(PERSISTENT);
        }
        return false;
    }

//    @Override
//    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state) {
//        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
//        if (!level.isClientSide()) {
//            level.levelEvent(2001, pos, Block.getId(state));
//        }
//
//        return new ItemStack(itemFruitStack.getItem());
//    }

//    public ItemStack setFruitItem(ItemStack itemFruitStack) {
//        return LodeFruitBlock.itemFruitStack = itemFruitStack;
//    }

//    @Override
//    public Optional<SoundEvent> getPickupSound() {
//        return Optional.empty();
//    }
}
