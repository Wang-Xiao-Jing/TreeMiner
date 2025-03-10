package ctn.tree_miner;

import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.CreativeItemsModeTab;
import ctn.tree_miner.create.TreeMinerItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.XiaoJinAPI;

@Mod(TreeMinerMain.MOD_ID)
public class TreeMinerMain {
    public static final String MOD_ID = "tree_miner";
    public TreeMinerMain(IEventBus modEventBus, ModContainer modContainer){
        TreeMinerBlocks.BLOCKS.register(modEventBus);
        TreeMinerItems.ITEMS.register(modEventBus);
        CreativeItemsModeTab.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(CreativeItemsModeTab::registerCapabilities);
    }

    public static ResourceLocation prefix(String name) {
        return XiaoJinAPI.prefix(MOD_ID, name);
    }
}
