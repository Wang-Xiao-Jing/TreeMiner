package ctn.tree_miner.common.items;

import com.google.common.collect.Maps;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 矿果杂炖
 */
public class OreStewItem extends Item {
    public static final HashMap<Holder<Item>, ItemFinishUsing> EFFECT_TABLE = Maps.newHashMap();

    public OreStewItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        var itemBack = super.finishUsingItem(stack, level, livingEntity);
        var dat = stack.getComponents().get(DataComponents.CUSTOM_DATA);
        if (Objects.isNull(dat)) {
            return itemBack;
        }

        var tag = dat.copyTag();
        if (!tag.contains("ore_name")) {
            return itemBack;
        }

        var name = ResourceLocation.tryParse(tag.getString("ore_name"));
        if (Objects.isNull(name)) {
            return itemBack;
        }

        var opt = BuiltInRegistries.ITEM.get(name);
        if (opt.isEmpty()) {
            return itemBack;
        }

        var backFunc = findOut(opt.get());
        if (Objects.isNull(backFunc)) {
            return itemBack;
        }

        backFunc.onFinishUsing(stack, level, livingEntity);

        return itemBack;
    }

    @Nullable
    public ItemFinishUsing findOut(Holder<Item> item) {
        return EFFECT_TABLE
                .entrySet()
                .stream()
                .filter(it -> item.is(it.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }


    public static interface ItemFinishUsing {
        void onFinishUsing(ItemStack item, Level world, LivingEntity entity);
    }
}
