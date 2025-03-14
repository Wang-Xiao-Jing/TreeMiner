package ctn.tree_miner.datagen;

import ctn.tree_miner.datagen.loot.TreeMinerBlockLootTableProvider;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import ctn.tree_miner.datagen.tags.TreeMinerItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xiao_jin.api.datagen.XiaoJinDatagen;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.datagen.TreeMinerDatapackProvider.BUILDER;

/**
 * @author 尽
 * @apiNote 数据生成
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class TreeMinerDatagen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        event.createDatapackRegistryObjects(BUILDER, Set.of(MOD_ID));
        event.createProvider(TreeMinerModelProvider::new);
        event.createProvider(TreeMinerLanguageProvider::new);
        event.createProvider(TreeMinerRecipeProvider.Runner::new);
        event.createBlockAndItemTags(TreeMinerBlockTags::new, TreeMinerItemTags::new);
//        gen.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
//                List.of(new LootTableProvider.SubProviderEntry(TreeMinerBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
//        gen.addProvider(true, new TreeMinerRecipeProvider.Runner(packOutput, lookupProvider));
//        event.createProvider(TreeMinerRecipeProvider.Runner::new);

    }
}
