package ctn.tree_miner.server.blocks.leaves;

import com.mojang.serialization.MapCodec;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import ctn.tree_miner.server.blocks.pod.PodBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

/**
 * 矿脉树叶
 */
public class LodeLeavesBlock extends LeavesBlock{
    public static final MapCodec<LodeLeavesBlock> CODEC = simpleCodec(LodeLeavesBlock::new);
    public final PodBlock GENERATE_BLOCK;

    @Override
    public MapCodec<? extends LodeLeavesBlock> codec() {
        return CODEC;
    }

    public LodeLeavesBlock(Properties properties) {
        this(properties, null);
    }

    public LodeLeavesBlock(Properties properties, Block generateBlock) {
        super(properties);
        if(generateBlock instanceof PodBlock block) {
            GENERATE_BLOCK = block;
        }else {
            GENERATE_BLOCK = null;
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    protected void randomTick(BlockState state,  ServerLevel level,  BlockPos pos,  RandomSource random) {
        if (level.getBlockState(pos.below()).isAir() && state.getValue(DISTANCE) != 7 && GENERATE_BLOCK != null) {
            produceFruit(level, random, pos, state);
        }
        super.randomTick(state, level, pos, random);
    }

    /** 生成 */
    protected void produceFruit(ServerLevel level, RandomSource random, BlockPos pos, BlockState state){
        level.setBlock(pos.below(), PodBlock.createNewFruitMature(GENERATE_BLOCK), 2);
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
