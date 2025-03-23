package ctn.tree_miner.common;

import com.google.common.collect.Sets;
import ctn.tree_miner.common.items.OreStewItem;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public class OreStewRecipe extends CustomRecipe {
    public OreStewRecipe(CraftingBookCategory func) {
        super(CraftingBookCategory.MISC);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        if (craftingInput.size() < 2) {
            return false;
        }

        final Set<Item> itemInput = Sets.newHashSet();
        boolean hasBowl = false;
        boolean hasGlowStone = false;

        for(int i = 0; i < craftingInput.size(); i++) {
            final ItemStack item = craftingInput.getItem(i);
            if (item.isEmpty()) {
                continue;
            }

            if (item.is(TreeMinerItems.LODE_BOWL)) {
                if (!hasBowl) {
                    hasBowl = true;
                    continue;
                }
                return false;
            }

            if (item.is(Blocks.GLOWSTONE.asItem())) {
                if (!hasGlowStone) {
                    hasGlowStone = true;
                    continue;
                }
                return false;
            }

            if (!OreStewItem.findAny(item) || itemInput.contains(item.getItem())) {
                return false;
            }

            itemInput.add(item.getItem());
        }

        return hasBowl;
    }

    @Override
    public @NotNull ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        boolean hasGlowStone = false;
        List<StringTag> items = Lists.newArrayList();

        for(int i = 0; i < craftingInput.size(); i++) {
            final ItemStack item = craftingInput.getItem(i);
            if (item.is(TreeMinerItems.LODE_BOWL)) {
                continue;
            }

            if (item.is(Blocks.GLOWSTONE.asItem())) {
                hasGlowStone = true;
                continue;
            }

            if (OreStewItem.findAny(item)) {
                String name = BuiltInRegistries.ITEM.getKey(item.getItem()).toString();
                items.add(StringTag.valueOf(name));
            }
        }

        ListTag tag = new ListTag();
        tag.addAll(items);

        ItemStack itemOutput = TreeMinerItems.ORE_STEW.toStack();

        final boolean finalHasGlowStone = hasGlowStone;
        var data = itemOutput.getComponents()
                .getOrDefault(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag()))
                .update(it -> {
                    it.put("ore_name", tag);
                    it.putBoolean("has_glowstone", finalHasGlowStone);
                });

        itemOutput.set(DataComponents.CUSTOM_DATA, data);

        return itemOutput;
    }

    @Override
    public @NotNull RecipeSerializer<OreStewRecipe> getSerializer() {
        return TreeMinerRecipes.ORE_STEW_RECIPE_SERIALIZER.get();
    }
}
