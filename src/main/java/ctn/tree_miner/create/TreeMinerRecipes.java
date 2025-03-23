package ctn.tree_miner.create;

import ctn.tree_miner.common.OreStewRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

public class TreeMinerRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MOD_ID);

    public static DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OreStewRecipe>> ORE_STEW_RECIPE_SERIALIZER =
            RECIPES.register("orestew", () -> new CustomRecipe.Serializer<>(OreStewRecipe::new));
}
