package ctn.tree_miner.datagen.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import xiao_jin.api.create.worldgen.XiaoJinPlacedFeatures;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerPlacedFeatures extends XiaoJinPlacedFeatures {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        XiaoJinPlacedFeatures.bootstrap(context);
    }
    protected static ResourceKey<PlacedFeature> registerKey(String name) {
        return XiaoJinPlacedFeatures.registerKey(MOD_ID, name);
    }

}
