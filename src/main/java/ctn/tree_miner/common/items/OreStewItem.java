package ctn.tree_miner.common.items;

import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;

import java.util.HashMap;

/**
 * 矿果杂炖
 */
public class OreStewItem extends Item {
    public static final HashMap<Holder<Item>, SuspiciousStewEffects> EFFECT_TABLE = Maps.newHashMap();

    public OreStewItem(Properties properties) {
        super(properties);
    }


}
