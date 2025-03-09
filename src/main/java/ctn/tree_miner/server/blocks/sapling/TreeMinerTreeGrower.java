package ctn.tree_miner.server.blocks.sapling;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * 矿树节的 TreeMinerTreeGrower 类，用于管理不同树种的生长逻辑。
 * <p>
 * 该类基于 {@linkplain net.minecraft.world.level.block.grower.TreeGrower} 实现。
 */
public class TreeMinerTreeGrower {
    private static final Map<String, TreeMinerTreeGrower> GROWERS = new Object2ObjectArrayMap<>();
    public static final Codec<TreeMinerTreeGrower> CODEC = Codec.stringResolver(
            toString -> toString.name, GROWERS::get);
    // 树种
    public static final TreeMinerTreeGrower OAK = new TreeMinerTreeGrower(
            "oak",
            0.1F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(TreeFeatures.OAK),
            Optional.of(TreeFeatures.FANCY_OAK),
            Optional.of(TreeFeatures.OAK_BEES_005),
            Optional.of(TreeFeatures.FANCY_OAK_BEES_005)
    );
    // 树种
    public static final TreeMinerTreeGrower SPRUCE = new TreeMinerTreeGrower(
            "spruce",
            0.5F,
            Optional.of(TreeFeatures.MEGA_SPRUCE),
            Optional.of(TreeFeatures.MEGA_PINE),
            Optional.of(TreeFeatures.SPRUCE),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
    );
    public static final TreeMinerTreeGrower MANGROVE = new TreeMinerTreeGrower(
            "mangrove",
            0.85F,
            Optional.empty(),
            Optional.empty(),
            Optional.of(TreeFeatures.MANGROVE),
            Optional.of(TreeFeatures.TALL_MANGROVE),
            Optional.empty(),
            Optional.empty()
    );
    public static final TreeMinerTreeGrower AZALEA = new TreeMinerTreeGrower("azalea", Optional.empty(), Optional.of(TreeFeatures.AZALEA_TREE), Optional.empty());
    public static final TreeMinerTreeGrower BIRCH = new TreeMinerTreeGrower("birch", Optional.empty(), Optional.of(TreeFeatures.BIRCH), Optional.of(TreeFeatures.BIRCH_BEES_005));
    public static final TreeMinerTreeGrower JUNGLE = new TreeMinerTreeGrower(
            "jungle", Optional.of(TreeFeatures.MEGA_JUNGLE_TREE), Optional.of(TreeFeatures.JUNGLE_TREE_NO_VINE), Optional.empty()
    );
    public static final TreeMinerTreeGrower ACACIA = new TreeMinerTreeGrower("acacia", Optional.empty(), Optional.of(TreeFeatures.ACACIA), Optional.empty());
    public static final TreeMinerTreeGrower CHERRY = new TreeMinerTreeGrower(
            "cherry", Optional.empty(), Optional.of(TreeFeatures.CHERRY), Optional.of(TreeFeatures.CHERRY_BEES_005)
    );
    public static final TreeMinerTreeGrower DARK_OAK = new TreeMinerTreeGrower("dark_oak", Optional.of(TreeFeatures.DARK_OAK), Optional.empty(), Optional.empty());
    public static final TreeMinerTreeGrower PALE_OAK = new TreeMinerTreeGrower("pale_oak", Optional.of(TreeFeatures.PALE_OAK_BONEMEAL), Optional.empty(), Optional.empty());
    private final String name;
    private final float secondaryChance;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

    /**
     * 构造函数，用于初始化 TreeMinerTreeGrower 实例。
     *
     * @param name 树种的名称
     * @param megaTree 该树种的大型树特征
     * @param tree 该树种的普通树特征
     * @param flowers 该树种的花特征
     */
    public TreeMinerTreeGrower(
            String name,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers
    ) {
        this(name, 0.0F, megaTree, Optional.empty(), tree, Optional.empty(), flowers, Optional.empty());
    }

    /**
     * 构造函数，用于初始化 TreeMinerTreeGrower 实例。
     *
     * @param name 树种的名称
     * @param secondaryChance 次级特征生成的概率
     * @param megaTree 该树种的大型树特征
     * @param secondaryMegaTree 该树种的次级大型树特征
     * @param tree 该树种的普通树特征
     * @param secondaryTree 该树种的次级普通树特征
     * @param flowers 该树种的花特征
     * @param secondaryFlowers 该树种的次级花特征
     */
    public TreeMinerTreeGrower(
            String name,
            float secondaryChance,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers,
            Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers
    ) {
        this.name = name;
        this.secondaryChance = secondaryChance;
        this.megaTree = megaTree;
        this.secondaryMegaTree = secondaryMegaTree;
        this.tree = tree;
        this.secondaryTree = secondaryTree;
        this.flowers = flowers;
        this.secondaryFlowers = secondaryFlowers;
        GROWERS.put(name, this);
    }

    /**
     * 根据随机数和是否生成花特征，获取配置的特征。
     *
     * @param random 随机数生成器
     * @param flowers 是否生成花特征
     * @return 配置的特征，如果不存在则返回 null
     */
    @Nullable
    private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean flowers) {
        if (random.nextFloat() < this.secondaryChance) {
            if (flowers && this.secondaryFlowers.isPresent()) {
                return this.secondaryFlowers.get();
            }

            if (this.secondaryTree.isPresent()) {
                return this.secondaryTree.get();
            }
        }

        return flowers && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse(null);
    }

    /**
     * 根据随机数获取配置的大型树特征。
     *
     * @param random 随机数生成器
     * @return 配置的大型树特征，如果不存在则返回 null
     */
    @Nullable
    private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return this.secondaryMegaTree.isPresent() && random.nextFloat() < this.secondaryChance ? this.secondaryMegaTree.get() : this.megaTree.orElse(null);
    }

    /**
     * 尝试在指定位置生成树。
     *
     * @param level 服务器世界
     * @param chunkGenerator 区块生成器
     * @param pos 生成位置
     * @param state 当前方块状态
     * @param random 随机数生成器
     * @return 如果生成成功则返回 true，否则返回 false
     */
    public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
        ResourceKey<ConfiguredFeature<?, ?>> resourcekey = this.getConfiguredMegaFeature(random);
        if (resourcekey != null) {
            Holder<ConfiguredFeature<?, ?>> holder = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(resourcekey).orElse(null);
            var event = net.neoforged.neoforge.event.EventHooks.fireBlockGrowFeature(level, random, pos, holder);
            holder = event.getFeature();
            if (event.isCanceled()) return false;
            if (holder != null) {
                for (int i = 0; i >= -1; i--) {
                    for (int j = 0; j >= -1; j--) {
                        if (isTwoByTwoSapling(state, level, pos, i, j)) {
                            ConfiguredFeature<?, ?> configuredfeature = holder.value();
                            BlockState blockstate = Blocks.AIR.defaultBlockState();
                            level.setBlock(pos.offset(i, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i + 1, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i, 0, j + 1), blockstate, 4);
                            level.setBlock(pos.offset(i + 1, 0, j + 1), blockstate, 4);
                            if (configuredfeature.place(level, chunkGenerator, random, pos.offset(i, 0, j))) {
                                return true;
                            }

                            level.setBlock(pos.offset(i, 0, j), state, 4);
                            level.setBlock(pos.offset(i + 1, 0, j), state, 4);
                            level.setBlock(pos.offset(i, 0, j + 1), state, 4);
                            level.setBlock(pos.offset(i + 1, 0, j + 1), state, 4);
                            return false;
                        }
                    }
                }
            }
        }

        ResourceKey<ConfiguredFeature<?, ?>> resourcekey1 = this.getConfiguredFeature(random, this.hasFlowers(level, pos));
        if (resourcekey1 == null) {
            return false;
        } else {
            Holder<ConfiguredFeature<?, ?>> holder1 = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(resourcekey1).orElse(null);
            var event = net.neoforged.neoforge.event.EventHooks.fireBlockGrowFeature(level, random, pos, holder1);
            holder1 = event.getFeature();
            if (event.isCanceled()) return false;
            if (holder1 == null) {
                return false;
            } else {
                ConfiguredFeature<?, ?> configuredfeature1 = holder1.value();
                BlockState blockstate1 = level.getFluidState(pos).createLegacyBlock();
                level.setBlock(pos, blockstate1, 4);
                if (configuredfeature1.place(level, chunkGenerator, random, pos)) {
                    if (level.getBlockState(pos) == blockstate1) {
                        level.sendBlockUpdated(pos, state, blockstate1, 2);
                    }

                    return true;
                } else {
                    level.setBlock(pos, state, 4);
                    return false;
                }
            }
        }
    }

    /**
     * 检查指定位置的方块是否为 2x2 的树苗。
     *
     * @param state 当前方块状态
     * @param level 方块获取器
     * @param pos 检查位置
     * @param xOffset x 轴偏移
     * @param yOffset y 轴偏移
     * @return 如果是 2x2 的树苗则返回 true，否则返回 false
     */
    private static boolean isTwoByTwoSapling(BlockState state, BlockGetter level, BlockPos pos, int xOffset, int yOffset) {
        Block block = state.getBlock();
        return level.getBlockState(pos.offset(xOffset, 0, yOffset)).is(block)
                && level.getBlockState(pos.offset(xOffset + 1, 0, yOffset)).is(block)
                && level.getBlockState(pos.offset(xOffset, 0, yOffset + 1)).is(block)
                && level.getBlockState(pos.offset(xOffset + 1, 0, yOffset + 1)).is(block);
    }

    /**
     * 检查指定位置周围是否有花。
     *
     * @param level 世界访问器
     * @param pos 检查位置
     * @return 如果有花则返回 true，否则返回 false
     */
    private boolean hasFlowers(LevelAccessor level, BlockPos pos) {
        for (BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
            if (level.getBlockState(blockpos).is(BlockTags.FLOWERS)) {
                return true;
            }
        }

        return false;
    }
}
