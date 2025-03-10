package ctn.tree_miner.create.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import xiao_jin.api.create.worldgen.ConfiguredFeaturesAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;

public class TreeMinerConfiguredFeatures extends ConfiguredFeaturesAPI {

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
        ConfiguredFeaturesAPI.bootstrap(context);
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

    public static void registerBasics(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                      ResourceKey<ConfiguredFeature<?, ?>> tree,
                                      Block log, Block leaves, Block dirt) {
        register(context, tree, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(log),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(leaves),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).dirt(BlockStateProvider.simple(dirt)).build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ConfiguredFeaturesAPI.registerKey(MOD_ID, name);
    }
}
