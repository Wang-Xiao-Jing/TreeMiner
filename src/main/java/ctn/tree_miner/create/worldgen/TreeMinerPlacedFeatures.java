package ctn.tree_miner.create.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import xiao_jin.api.create.worldgen.PlacedFeaturesAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerPlacedFeatures extends PlacedFeaturesAPI {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        PlacedFeaturesAPI.bootstrap(context);
    }
    protected static ResourceKey<PlacedFeature> registerKey(String name) {
        return PlacedFeaturesAPI.registerKey(MOD_ID, name);
    }

}
