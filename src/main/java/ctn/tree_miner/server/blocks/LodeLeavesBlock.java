package ctn.tree_miner.server.blocks;

import com.mojang.serialization.MapCodec;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import ctn.tree_miner.server.property.TreeMinerProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

/**
 * 矿脉树叶
 */
public class LodeLeavesBlock extends LeavesBlock{
    public static final BooleanProperty IS_FRUIT_BEARING = TreeMinerProperty.FRUIT_BEARING;
    public static final MapCodec<LodeLeavesBlock> CODEC = simpleCodec(LodeLeavesBlock::new);

    /**
     * 获取当前块的编解码器
     * @return 当前块的编解码器
     */
    @Override
    public  MapCodec<? extends LodeLeavesBlock> codec() {
        return CODEC;
    }

    /**
     * 构造函数，初始化矿脉树叶块的默认状态
     * @param properties 块的属性
     */
    public LodeLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(IS_FRUIT_BEARING, Boolean.FALSE)
        );
    }

    /**
     * 随机刻处理，检查是否结果并生成果实
     * @param state 当前块状态
     * @param level 服务器世界
     * @param pos 块位置
     * @param random 随机源
     */
    @Override
    protected void randomTick(BlockState state,  ServerLevel level,  BlockPos pos,  RandomSource random) {
        if (stateDefinition.any().getValue(IS_FRUIT_BEARING) && level.getBlockState(pos.below()).isAir()) {
            produceFruit(level, random, pos, state);
        }
    }

    /**
     * 检查当前块是否结果
     * @param state 当前块状态
     * @return 如果块结果则返回true，否则返回false
     */
    protected boolean isFruitBearing(BlockState state){
        return !state.getValue(IS_FRUIT_BEARING) && state.getValue(DISTANCE) == 7;
    }

    /**
     * 刻处理，更新块的距离并设置结果状态
     * @param state 当前块状态
     * @param level 服务器世界
     * @param pos 块位置
     * @param random 随机源
     */
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random){
        level.setBlock(pos, updateDistance(state, level, pos), 3);
        stateDefinition.any().setValue(IS_FRUIT_BEARING, this.isFruitBearing(state));
    }

    /**
     * 生成果实
     * @param level 服务器世界
     * @param random 随机源
     * @param pos 块位置
     * @param state 当前块状态
     */
    protected void produceFruit(ServerLevel level, RandomSource random, BlockPos pos, BlockState state){
        level.setBlock(pos.below(), MangrovePropaguleBlock.createNewHangingPropagule(), 2);
    }

    /**
     * 创建块状态定义
     * @param builder 块状态定义构建器
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(IS_FRUIT_BEARING);
    }

    /**
     * 更新块的形状，处理水logged状态和距离更新
     * @param state 当前块状态
     * @param leve 世界读取器
     * @param access 计划刻访问器
     * @param pos 块位置
     * @param direction 方向
     * @param adjoinPos 相邻块位置
     * @param adjoinState 相邻块状态
     * @param source 随机源
     * @return 更新后的块状态
     */
    @Override
    protected BlockState updateShape(BlockState state, LevelReader leve, ScheduledTickAccess access, BlockPos pos,
                                     Direction direction, BlockPos adjoinPos, BlockState adjoinState, RandomSource source) {
        if (state.getValue(WATERLOGGED)) {
            access.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(leve));
        }

        int i = getDistanceAt(adjoinState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i) {
            access.scheduleTick(pos, this, 1);
        }

        return state;
    }

    /**
     * 更新块的距离
     * @param state 当前块状态
     * @param level 世界访问器
     * @param pos 块位置
     * @return 更新后的块状态
     */
    public static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceAt(level.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, Integer.valueOf(i));
    }

    /**
     * 获取相邻块的距离
     * @param neighbor 相邻块状态
     * @return 相邻块的距离
     */
    public static int getDistanceAt(BlockState neighbor) {
        if (neighbor.is(TreeMinerBlocks.LODE_LOG.Block()) || neighbor.is(TreeMinerBlockTags.BlockTags.LODE_LEAVES)) {
            return getOptionalDistanceAt(neighbor).orElse(7);
        }
        return 7;
    }

    /**
     * 获取相邻块的可选距离
     * @param state 相邻块状态
     * @return 相邻块的可选距离
     */
    public static OptionalInt getOptionalDistanceAt(BlockState state) {
        if (state.is(TreeMinerBlockTags.BlockTags.LODE_LOG)) {
            return OptionalInt.of(0);
        } else {
            return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    /**
     * 获取玩家放置块时的状态
     * @param context 块放置上下文
     * @return 放置后的块状态
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockstate = this.defaultBlockState()
                .setValue(PERSISTENT, Boolean.valueOf(true))
                .setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        return updateDistance(blockstate, context.getLevel(), context.getClickedPos());
    }
}
