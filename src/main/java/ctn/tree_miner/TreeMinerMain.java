package ctn.tree_miner;

import ctn.tree_miner.common.items.OreStewItem;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerRecipes;
import ctn.tree_miner.create.TreeMinerTab;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Mod(TreeMinerMain.MOD_ID)
public class TreeMinerMain {
    public static final String MOD_ID = "tree_miner";
    public TreeMinerMain(IEventBus modEventBus, ModContainer modContainer){
        TreeMinerItems.ITEMS.register(modEventBus);
        TreeMinerBlocks.BLOCKS.register(modEventBus);
        TreeMinerRecipes.RECIPES.register(modEventBus);
        TreeMinerTab.CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(TreeMinerTab::registerCapabilities);

        this.initRecipe();
    }


    // TODO 可能做不到要求，1.没有等级， 2.要可以混搭（因为萤石需要提升这些等级）
    public void initRecipe() {
        put(TreeMinerItems.POD_COAL, createEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 3)));
    }

    public void put(Holder<Item> item, OreStewItem.ItemFinishUsing using) {
        OreStewItem.EFFECT_TABLE.put(item, using);
    }

    public OreStewItem.ItemFinishUsing createEffect(MobEffectInstance effect) {
        return (item, world, entity) -> {
            var data = item.getComponents().get(DataComponents.CUSTOM_DATA);
            assert Objects.nonNull(data);

            AtomicBoolean glowstone = new AtomicBoolean(false);
            data.update(it -> {
                glowstone.set(it.getBoolean("has_glowstone"));
            });

            final var effectIn = glowstone.get()
                    ? new MobEffectInstance(effect.getEffect(), effect.getDuration() / 2, effect.getAmplifier() + 1)
                    : new MobEffectInstance(effect);

            entity.addEffect(effectIn);
        };
    }
}
