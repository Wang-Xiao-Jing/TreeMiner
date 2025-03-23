package ctn.tree_miner.common.items;

import com.google.common.collect.Maps;
import ctn.tree_miner.create.TreeMinerItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
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
        if (level.isClientSide) {
            return itemBack;
        }

        if (livingEntity instanceof Player player) {
            returnBowlToPlayer(player, level);
        }

        var dat = stack.getComponents().get(DataComponents.CUSTOM_DATA);
        if (Objects.isNull(dat)) {
            return itemBack;
        }

        var tag = dat.copyTag();
        if (!tag.contains("ore_name")) {
            return itemBack;
        }

        var list = tag.getList("ore_name", Tag.TAG_STRING);
        if (list.isEmpty()) {
            return itemBack;
        }

        list.forEach(it -> {
            var name = ResourceLocation.tryParse(it.getAsString());

            var opt = BuiltInRegistries.ITEM.get(name);
            if (opt.isEmpty()) {
                return;
            }

            var backFunc = findOut(opt.get());
            if (Objects.isNull(backFunc)) {
                return;
            }

            backFunc.onFinishUsing(stack, level, livingEntity);
        });

        return itemBack;
    }

    private static void returnBowlToPlayer(Player player, Level world) {
        ItemStack stackBowl = TreeMinerItems.LODE_BOWL.toStack();
        if (player.isCreative())

            if (!player.addItem(stackBowl)) {
                ItemEntity entity = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stackBowl);
                entity.setDefaultPickUpDelay();
                world.addFreshEntity(entity);
            }
    }

    @Override
    public ItemStack getCraftingRemainder(ItemStack itemStack) {
        return TreeMinerItems.LODE_BOWL.toStack();
    }

    @Nullable
    public static ItemFinishUsing findOut(Holder<Item> item) {
        return EFFECT_TABLE
                .entrySet()
                .stream()
                .filter(it -> item.is(it.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    @Nullable
    public static ItemFinishUsing findOut(ItemStack item) {
        return EFFECT_TABLE
                .entrySet()
                .stream()
                .filter(it -> item.is(it.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    public static Boolean findAny(ItemStack item) {
        return Objects.nonNull(EFFECT_TABLE
                .entrySet()
                .stream()
                .filter(it -> item.is(it.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null));
    }

    @FunctionalInterface
    public interface ItemFinishUsing {
        void onFinishUsing(ItemStack item, Level world, LivingEntity entity);
    }
}
