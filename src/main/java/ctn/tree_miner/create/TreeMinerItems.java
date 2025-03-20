package ctn.tree_miner.create;

import ctn.tree_miner.common.OreStew;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static net.minecraft.world.effect.MobEffects.*;
import static net.minecraft.world.item.Items.BOWL;
import static net.minecraft.world.item.component.Consumables.defaultFood;

/**
 * @author 尽
 * @apiNote 创建物品
 */
public class TreeMinerItems{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredItem<Item> POD_COAL = ITEMS.registerItem("pod_coal", Item::new);
    public static final DeferredItem<Item> POD_IRON = ITEMS.registerItem("pod_iron", Item::new);
    public static final DeferredItem<Item> POD_COPPER = ITEMS.registerItem("pod_copper", Item::new);
    public static final DeferredItem<Item> POD_LAPIS = ITEMS.registerItem("pod_lapis", Item::new);
    public static final DeferredItem<Item> POD_EMERALD = ITEMS.registerItem("pod_emerald", Item::new);
    public static final DeferredItem<Item> POD_GOLD = ITEMS.registerItem("pod_gold", Item::new);
    public static final DeferredItem<Item> POD_REDSTONE = ITEMS.registerItem("pod_redstone", Item::new);
    public static final DeferredItem<Item> POD_DIAMOND = ITEMS.registerItem("pod_diamond", Item::new);
    public static final DeferredItem<Item> NETHER_POD_GLOWSTONE = ITEMS.registerItem("nether_pod_glowstone", Item::new);
    public static final DeferredItem<Item> NETHER_POD_QUARTZ = ITEMS.registerItem("nether_pod_quartz", Item::new);
    public static final DeferredItem<Item> NETHER_POD_GOLD = ITEMS.registerItem("nether_pod_gold", Item::new);
    public static final DeferredItem<Item> NETHER_POD_NETHERITE = ITEMS.registerItem("nether_pod_netherite", Item::new, new Item.Properties().fireResistant());

    public static final DeferredItem<Item> COOKED_POD_COAL = createCookedPod("cooked_pod_coal", Foods.COAL, Consumables.COAL);
    public static final DeferredItem<Item> COOKED_POD_IRON = createCookedPod("cooked_pod_iron", Foods.IRON, Consumables.IRON);
    public static final DeferredItem<Item> COOKED_POD_COPPER = createCookedPod("cooked_pod_copper", Foods.COPPER, Consumables.COPPER);
    public static final DeferredItem<Item> COOKED_POD_LAPIS = createCookedPod("cooked_pod_lapis", Foods.LAPIS, Consumables.LAPIS);
    public static final DeferredItem<Item> COOKED_POD_EMERALD = createCookedPod("cooked_pod_emerald", Foods.EMERALD, Consumables.EMERALD);
    public static final DeferredItem<Item> COOKED_POD_GOLD = createCookedPod("cooked_pod_gold", Foods.GOLD, Consumables.GOLD);
    public static final DeferredItem<Item> COOKED_POD_REDSTONE = createCookedPod("cooked_pod_redstone", Foods.REDSTONE, Consumables.REDSTONE);
    public static final DeferredItem<Item> COOKED_POD_DIAMOND = createCookedPod("cooked_pod_diamond", Foods.DIAMOND, Consumables.DIAMOND);
    public static final DeferredItem<Item> COOKED_NETHER_POD_GLOWSTONE = createCookedPod("cooked_nether_pod_glowstone", Foods.GLOWSTONE, Consumables.GLOWSTONE);
    public static final DeferredItem<Item> COOKED_NETHER_POD_QUARTZ = createCookedPod("cooked_nether_pod_quartz", Foods.QUARTZ, Consumables.QUARTZ);
    public static final DeferredItem<Item> COOKED_NETHER_POD_GOLD = createCookedPod("cooked_nether_pod_gold", Foods.NETHER_GOLD, Consumables.NETHER_GOLD);
    public static final DeferredItem<Item> COOKED_NETHER_POD_NETHERITE = createCookedPod("cooked_nether_pod_netherite", new Item.Properties().fireResistant().food(Foods.NETHERITE, Consumables.NETHERITE).fireResistant());

    public static final DeferredItem<Item> LODE_BOWL = ITEMS.registerItem("lode_bowl", Item::new);
    public static final DeferredItem<OreStew> ORE_STEW = ITEMS.registerItem("ore_stew", OreStew::new,
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(4)
                    .saturationModifier(0.1f)
                    .alwaysEdible()
                    .build())
            .component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY)
            .craftRemainder(BOWL) // TODO 无法获取MOD物品
            .usingConvertsTo(BOWL));

    public static DeferredItem<Item> createCookedPod(String name, Item.Properties properties){
        return ITEMS.registerItem(name, Item::new, properties);
    }

    public static DeferredItem<Item> createCookedPod(String name, FoodProperties foodproperties, Consumable consumable){
        return ITEMS.registerItem(name, Item::new, new Item.Properties().food(foodproperties, consumable));
    }

    public static class Foods{
        public static final FoodProperties COAL = createFoodProperties(2, 0.1F);
        public static final FoodProperties IRON = createFoodProperties(4, 0.2F);
        public static final FoodProperties COPPER = createFoodProperties(3, 0.2F);
        public static final FoodProperties LAPIS = createFoodProperties(3, 0.2F);
        public static final FoodProperties EMERALD = createFoodProperties(4, 0.3F);
        public static final FoodProperties GOLD = createFoodProperties(2, 0.4F);
        public static final FoodProperties REDSTONE = createFoodProperties(3, 0.2F);
        public static final FoodProperties DIAMOND = createFoodProperties(5, 0.4F);
        public static final FoodProperties GLOWSTONE = createFoodProperties(3, 0.2F);
        public static final FoodProperties QUARTZ = createFoodProperties(3, 0.2F);
        public static final FoodProperties NETHER_GOLD = createFoodProperties(1, 0.4F);
        public static final FoodProperties NETHERITE = createFoodProperties(6, 0.4F);
    }

    public static class Consumables{
        public static final Consumable COAL = createConsumable(NIGHT_VISION, 20 * 60);
        public static final Consumable IRON = createConsumable(DAMAGE_RESISTANCE, 20 * 15, 2);
        public static final Consumable COPPER = createConsumable(DAMAGE_RESISTANCE, 20 * 30);
        public static final Consumable LAPIS = createConsumable(WATER_BREATHING, 20 * 60);
        public static final Consumable EMERALD = createConsumable(HERO_OF_THE_VILLAGE, 20 * 60);
        public static final Consumable GOLD = createConsumable(FIRE_RESISTANCE, 20 * 60);
        public static final Consumable REDSTONE = createConsumable(DIG_SPEED, 20 * 60);
        public static final Consumable DIAMOND = createConsumable(ABSORPTION, 20 * 30, 3);
        public static final Consumable GLOWSTONE = createConsumable(GLOWING, 20 * 60);
        public static final Consumable QUARTZ = createConsumable(DAMAGE_BOOST, 20 * 30);
        public static final Consumable NETHER_GOLD = createConsumable(FIRE_RESISTANCE, 20 * 30);
        public static final Consumable NETHERITE = defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(
                        List.of(new MobEffectInstance(FIRE_RESISTANCE, 20 * 60, 1),
                                new MobEffectInstance(DAMAGE_RESISTANCE, 20 * 60, 3)
                        )))
                .build();
    }

    public static FoodProperties createFoodProperties(int hunger, float saturation){
        return new FoodProperties.Builder()
                .nutrition(hunger)
                .saturationModifier(saturation)
                .build();
    }

    public static Consumable createConsumable(Holder<MobEffect> effect, int duration, int amplifier){
        return defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(effect, duration, amplifier))))
                .build();
    }

    public static Consumable createConsumable(Holder<MobEffect> effect, int duration){
        return defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(effect, duration, 1))))
                .build();
    }
}
