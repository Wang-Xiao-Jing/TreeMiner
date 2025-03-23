package ctn.tree_miner;

import ctn.tree_miner.common.items.OreStewItem;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerRecipes;
import ctn.tree_miner.create.TreeMinerTab;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.List;
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

    public void onRegisterCall(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.ITEM)) {
            return;
        }


    }


    // TODO 可能做不到要求，1.没有等级， 2.要可以混搭（因为萤石需要提升这些等级）
    public void initRecipe() {
        put(TreeMinerItems.COOKED_POD_COAL);
        put(TreeMinerItems.COOKED_POD_IRON);
        put(TreeMinerItems.COOKED_POD_COPPER);
        put(TreeMinerItems.COOKED_POD_LAPIS);
        put(TreeMinerItems.COOKED_POD_EMERALD);
        put(TreeMinerItems.COOKED_POD_GOLD);
        put(TreeMinerItems.COOKED_POD_REDSTONE);
        put(TreeMinerItems.COOKED_POD_DIAMOND);
        put(TreeMinerItems.COOKED_NETHER_POD_GLOWSTONE);
        put(TreeMinerItems.COOKED_NETHER_POD_QUARTZ);
        put(TreeMinerItems.COOKED_NETHER_POD_GOLD);
        put(TreeMinerItems.COOKED_NETHER_POD_NETHERITE);
    }

    public void put(Holder<Item> item) {
        put(item, (stack, world, entity) -> {
            final ItemStack itm = item.value().getDefaultInstance();
            final Consumable consumable = itm.get(DataComponents.CONSUMABLE);
            if (Objects.isNull(consumable)) {
                return;
            }

            final CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            assert Objects.nonNull(data);

            final AtomicBoolean glowstone = new AtomicBoolean(false);
            data.update(it -> {
                glowstone.set(it.getBoolean("has_glowstone"));
            });

            final List<ConsumeEffect> effects = consumable.onConsumeEffects();
            effects.stream()
                    .filter(it -> it instanceof ApplyStatusEffectsConsumeEffect)
                    .map(it -> ((ApplyStatusEffectsConsumeEffect) it))
                    .map(ApplyStatusEffectsConsumeEffect::effects)
                    .flatMap(List::stream)
                    .map(it ->
                        glowstone.get()
                                ? new MobEffectInstance(it.getEffect(), it.getDuration() / 2, it.getAmplifier() + 1)
                                : new MobEffectInstance(it)
                    )
                    .forEach(entity::addEffect);
        });
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
