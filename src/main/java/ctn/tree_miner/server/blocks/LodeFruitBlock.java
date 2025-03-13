package ctn.tree_miner.server.blocks;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static ctn.tree_miner.create.TreeMinerBlocks.*;
import static net.minecraft.world.level.block.LeavesBlock.*;

/** 矿果模版 */
public class LodeFruitBlock extends Block implements BonemealableBlock {
    protected static final VoxelShape SHAPE = Block.box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0);
    protected static final VoxelShape SHAPE_MATURE = Block.box(3.0, 10.0, 3.0, 13.0, 16.0, 13.0);
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
        int age = state.getValue(STAGE_3);
        if (state.getValue(STAGE_3) != 3) {
            level.setBlockAndUpdate(pos, state.setValue(STAGE_3, ++age));
        }
        if (state.getValue(STAGE_3) == 3 && level.getBlockState(pos.above()).getBlock() instanceof LodeLeavesBlock block) {
            if (block.getStateDefinition().any().getValue(DISTANCE) == 7) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            }
        }
    }

    public static BlockState createNewFruitMature() {
        return createNewFruitMature(0);
    }

    public static BlockState createNewFruitMature(int age) {
        return TreeMinerBlocks.TEST_FRUIT_MATURE.get().defaultBlockState().setValue(STAGE_3, age);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

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
}
