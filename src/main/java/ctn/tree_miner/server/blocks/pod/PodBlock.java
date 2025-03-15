package ctn.tree_miner.server.blocks.pod;

import ctn.tree_miner.server.blocks.leaves.LodeLeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import static ctn.tree_miner.create.TreeMinerBlocks.POD_COAL_BLOCK;
import static ctn.tree_miner.create.TreeMinerBlocks.STAGE_3;
import static net.minecraft.world.level.block.LeavesBlock.DISTANCE;
import static net.minecraft.world.level.block.LeavesBlock.PERSISTENT;

/** 矿果方块模版 */
public class PodBlock extends Block{
    public PodBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE_3, 0));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
//        int age = state.getValue(STAGE_3);
        if (random.nextInt(3) != 0) {
            if (state.getValue(STAGE_3) != 3) {
                level.setBlockAndUpdate(pos, state.cycle(STAGE_3));
            }
            if (state.getValue(STAGE_3) == 3) {
                dropResources(state, level, pos);
                level.removeBlock(pos, false);
            }
        }
    }

    public static BlockState createNewFruitMature(PodBlock block) {
        return createNewFruitMature(0, block);
    }

    public static BlockState createNewFruitMature(int age, PodBlock block) {
        return block.defaultBlockState().setValue(STAGE_3, age);
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
