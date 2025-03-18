package ctn.tree_miner.datagen;

import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import ctn.tree_miner.datagen.tags.TreeMinerItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.datagen.TreeMinerDatapackProvider.BUILDER;

/**
 * 数据生成
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class TreeMinerDatagen {

    // TODO：最好别用 :(
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        event.createDatapackRegistryObjects(BUILDER, Set.of(MOD_ID)); // 注册数据包
        event.createProvider(TreeMinerLanguageProvider::new); // 语言生成
//        event.createBlockAndItemTags(TreeMinerBlockTags::new, TreeMinerItemTags::new); // 标签生成
        event.createProvider(TreeMinerModelProvider::new); // 模型生成
//        event.createProvider(TreeMinerRecipeProvider.Runner::new); // 配方生成
//        gen.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
//                List.of(new LootTableProvider.SubProviderEntry(TreeMinerBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider)); // 掉落物生成

    }
}
