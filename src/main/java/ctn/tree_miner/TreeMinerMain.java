package ctn.tree_miner;

import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(TreeMinerMain.MOD_ID)
public class TreeMinerMain {
    public static final String MOD_ID = "tree_miner";
    public TreeMinerMain(IEventBus modEventBus, ModContainer modContainer){
        TreeMinerItems.ITEMS.register(modEventBus);
        TreeMinerBlocks.BLOCKS.register(modEventBus);
        TreeMinerTab.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(TreeMinerTab::registerCapabilities);
    }
}
