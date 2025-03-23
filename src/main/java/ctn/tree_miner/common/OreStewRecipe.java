package ctn.tree_miner.common;

import ctn.tree_miner.common.items.OreStewItem;
import ctn.tree_miner.create.TreeMinerItems;
import ctn.tree_miner.create.TreeMinerRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OreStewRecipe extends CustomRecipe {
    public OreStewRecipe(CraftingBookCategory category) {
        super(CraftingBookCategory.MISC);
    }

    @Override
    public boolean matches(CraftingInput craftingInput, Level level) {
        var itemInputs = craftingInput.items();
        if (itemInputs.size() != 2) {
            return false;
        }

        if (itemInputs.stream().noneMatch(it -> it.is(Items.BOWL))) {
            return false;
        }

        var opt = itemInputs.stream()
                .filter(OreStewItem::findAny)
                .findFirst();

        return opt.isPresent();
    }

    @Override
    public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
        var out = craftingInput.items().stream()
                .filter(OreStewItem::findAny)
                .findFirst().get();

        String name = BuiltInRegistries.ITEM.getKey(out.getItem()).toString();

        ItemStack itemOutput = TreeMinerItems.ORE_STEW.toStack();
        itemOutput.getComponents().get(DataComponents.CUSTOM_DATA).update(it -> {
            it.putString("ore_name", name);
        });

        return itemOutput;
    }

    @Override
    public @NotNull RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return TreeMinerRecipes.ORE_STEW_RECIPE_SERIALIZER.get();
    }
}
