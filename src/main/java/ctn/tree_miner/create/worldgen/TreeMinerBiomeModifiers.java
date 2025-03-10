package ctn.tree_miner.create.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import xiao_jin.api.create.worldgen.BiomeModifiersAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerBiomeModifiers extends BiomeModifiersAPI {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        BiomeModifiersAPI.bootstrap(context);
    }
    protected static ResourceKey<BiomeModifier> registerKey( String name) {
        return BiomeModifiersAPI.registerKey(MOD_ID, name);
    }
}
