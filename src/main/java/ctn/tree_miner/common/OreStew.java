package ctn.tree_miner.common;

import com.google.common.collect.Maps;
import ctn.tree_miner.create.TreeMinerItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;

import java.util.HashMap;
import java.util.function.Supplier;

import static ctn.tree_miner.create.TreeMinerItems.LODE_BOWL;

/**
 * 矿果杂炖
 */
public class OreStew extends Item {
    public static final HashMap<Holder<Item>, SuspiciousStewEffects> EFFECT_TABLE = Maps.newHashMap();

    public OreStew(Properties properties) {
        super(properties);
    }


}
