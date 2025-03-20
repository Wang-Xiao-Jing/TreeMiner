package ctn.tree_miner.datagen.worldgen;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.world.BiomeModifier;
import xiao_jin.api.create.worldgen.XiaoJinBiomeModifiers;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerBiomeModifiers extends XiaoJinBiomeModifiers {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // CF -> PF -> BM
        XiaoJinBiomeModifiers.bootstrap(context);
    }
    protected static ResourceKey<BiomeModifier> registerKey( String name) {
        return XiaoJinBiomeModifiers.registerKey(MOD_ID, name);
    }
}
