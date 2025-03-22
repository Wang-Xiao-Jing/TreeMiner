package ctn.tree_miner;

import ctn.tree_miner.common.items.OreStewItem;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerTab;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
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


    // TODO 可能做不到要求，1.没有等级， 2.要可以混搭（因为萤石需要提升这些等级）
    @SubscribeEvent
    public void onCommonSetupEvent(FMLCommonSetupEvent event) {
        OreStewItem.EFFECT_TABLE.put(TreeMinerItems.ORE_STEW,
                createEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 3)));
    }

    public OreStewItem.ItemFinishUsing createEffect(MobEffectInstance effect) {
        return (item, world, entity) -> entity.addEffect(new MobEffectInstance(effect));
    }
}
