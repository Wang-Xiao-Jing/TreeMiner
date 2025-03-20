package ctn.tree_miner.common;

import com.google.common.collect.Maps;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;

import java.util.HashMap;

public class OreStew extends Item {
    public static final HashMap<Item, SuspiciousStewEffects> EFFECT_TABLE = Maps.newHashMap();

    public OreStew() {
        super(new Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.1f)
                        .alwaysEdible()
                        .build())
                .component(DataComponents.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffects.EMPTY)
                .craftRemainder(Items.BOWL)
                .usingConvertsTo(Items.BOWL)
        );
    }
}
