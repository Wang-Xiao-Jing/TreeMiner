package ctn.tree_miner.datagen;

import ctn.tree_miner.datagen.loot.BlockLootTableProvider;
import ctn.tree_miner.datagen.loot.LootTableProvider;
import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import ctn.tree_miner.datagen.tags.TreeMinerItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.internal.NeoForgeLanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xiao_jin.api.datagen.DatagenAPI;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

/**
 * @author 尽
 * @apiNote 数据生成
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = MOD_ID)
public class Datagen extends DatagenAPI {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator gen = event.getGenerator(); // 获取数据生成器
        PackOutput packOutput = gen.getPackOutput(); // 获取输出路径
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider(); // 获取查找提供者
        event.createProvider(ModelProvider::new);
        event.createProvider(LanguageProvider::new);
        event.createBlockAndItemTags(TreeMinerBlockTags::new, TreeMinerItemTags::new);
        gen.addProvider(event.includeReports(), new LootTableProvider(packOutput, List.of(new LootTableProvider.SubProviderEntry(BlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
    }
}
