package ctn.tree_miner.datagen;

import ctn.tree_miner.create.worldgen.TreeMinerBiomeModifiers;
import ctn.tree_miner.create.worldgen.TreeMinerConfiguredFeatures;
import ctn.tree_miner.create.worldgen.TreeMinerPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xiao_jin.api.datagen.XiaoJinDatapackProvider;

import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerDatapackProvider extends XiaoJinDatapackProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, TreeMinerConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, TreeMinerPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TreeMinerBiomeModifiers::bootstrap);

    public TreeMinerDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, MOD_ID);
    }
}
