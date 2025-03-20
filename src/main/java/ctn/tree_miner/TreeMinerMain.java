package ctn.tree_miner;

import ctn.tree_miner.common.OreStew;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerTab;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;

@Mod(TreeMinerMain.MOD_ID)
public class TreeMinerMain {
    public static final String MOD_ID = "tree_miner";
    public TreeMinerMain(IEventBus modEventBus, ModContainer modContainer){
        TreeMinerItems.ITEMS.register(modEventBus);
        TreeMinerBlocks.BLOCKS.register(modEventBus);
        TreeMinerTab.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(TreeMinerTab::registerCapabilities);
        modEventBus.register(this);
    }

    @SubscribeEvent
    public void onCommonSetupEvent(FMLCommonSetupEvent event) {
        OreStew.EFFECT_TABLE.put(TreeMinerItems.ORE_STEW, createEffect(MobEffects.DAMAGE_BOOST, 20 * 3));
    }

    public SuspiciousStewEffects createEffect(Holder<MobEffect> effect, int du) {
        return new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, du)));
    }
}
