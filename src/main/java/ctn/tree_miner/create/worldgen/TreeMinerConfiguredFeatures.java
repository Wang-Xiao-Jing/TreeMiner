package ctn.tree_miner.create.worldgen;

import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import xiao_jin.api.create.worldgen.XiaoJinConfiguredFeatures;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;

/** 树木生长（非自然） */
public class TreeMinerConfiguredFeatures extends XiaoJinConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_COAL_TREE = registerKey("lode_coal_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_IRON_TREE = registerKey("lode_iron_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_COPPER_TREE = registerKey("lode_copper_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_LAPIS_TREE = registerKey("lode_lapis_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_EMERALD_TREE = registerKey("lode_emerald_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_GOLD_TREE = registerKey("lode_gold_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_REDSTONE_TREE = registerKey("lode_redstone_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LODE_DIAMOND_TREE = registerKey("lode_diamond_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_LODE_QUARTZ_TREE = registerKey("nether_lode_quartz_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_LODE_GLOWSTONE_TREE = registerKey("nether_lode_glowstone_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_LODE_ANCIENT_DEBRIS_TREE = registerKey("nether_lode_ancient_debris_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_LODE_GOLD_TREE = registerKey("nether_lode_gold_tree");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        XiaoJinConfiguredFeatures.bootstrap(context);
        registerBasics(context, LODE_COAL_TREE, LODE_LEAVES_COAL.block());
        registerBasics(context, LODE_IRON_TREE, LODE_LEAVES_IRON.block());
        registerBasics(context, LODE_COPPER_TREE, LODE_LEAVES_COPPER.block());
        registerBasics(context, LODE_LAPIS_TREE, LODE_LEAVES_LAPIS.block());
        registerBasics(context, LODE_EMERALD_TREE, LODE_LEAVES_EMERALD.block());
        registerBasics(context, LODE_GOLD_TREE, LODE_LEAVES_GOLD.block());
        registerBasics(context, LODE_REDSTONE_TREE, LODE_LEAVES_REDSTONE.block());
        registerBasics(context, LODE_DIAMOND_TREE, LODE_LEAVES_DIAMOND.block());
        netherRegisterBasics(context, NETHER_LODE_QUARTZ_TREE, NETHER_LODE_LEAVES_QUARTZ.block());
        netherRegisterBasics(context, NETHER_LODE_GLOWSTONE_TREE, NETHER_LODE_LEAVES_GLOWSTONE.block());
        netherRegisterBasics(context, NETHER_LODE_ANCIENT_DEBRIS_TREE, NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block());
        netherRegisterBasics(context, NETHER_LODE_GOLD_TREE, NETHER_LODE_LEAVES_GOLD.block());
    }

    public static void registerBasics(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                      ResourceKey<ConfiguredFeature<?, ?>> tree,
                                      Block leaves){
        registerBasics(context, tree, LODE_LOG.block(), leaves, Blocks.STONE);
    }

    public static void netherRegisterBasics(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                      ResourceKey<ConfiguredFeature<?, ?>> tree,
                                      Block leaves){
        registerBasics(context, tree, NETHER_LODE_LOG.block(), leaves, Blocks.NETHERRACK);
    }

    /**
     * 注册树(樱花树模版)
     * @param context
     * @param tree
     * @param log 原木
     * @param leaves 树叶
     * @param dirt 下方替换的泥土
     */
    public static void registerBasics(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                      ResourceKey<ConfiguredFeature<?, ?>> tree,
                                      Block log, Block leaves, Block dirt) {
        if (leaves instanceof LeavesBlock leavesBlock){
            register(context, tree, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(log),
                    new CherryTrunkPlacer(7, 1, 0,
                            new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1)
                                    .add(ConstantInt.of(2), 1)
                                    .add(ConstantInt.of(3), 1).build()),
                            UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                    BlockStateProvider.simple(
                            leavesBlock.getStateDefinition().any()
                                    .setValue(LeavesBlock.PERSISTENT, false)
                                    .setValue(LeavesBlock.WATERLOGGED, false)
                    ),
                    new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                    new TwoLayersFeatureSize(1, 0, 2)
            ).dirt(BlockStateProvider.simple(dirt)).build());
        }
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return registerKey(MOD_ID, name);
    }
}
