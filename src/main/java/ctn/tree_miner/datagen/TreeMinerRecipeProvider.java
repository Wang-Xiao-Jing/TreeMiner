package ctn.tree_miner.datagen;

import com.google.common.collect.ImmutableList;
import ctn.tree_miner.create.TreeMinerBlocks;
import ctn.tree_miner.datagen.tags.TreeMinerItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.create.TreeMinerItems.*;
import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static net.minecraft.data.recipes.RecipeCategory.BUILDING_BLOCKS;
import static net.minecraft.data.recipes.RecipeCategory.MISC;
import static net.minecraft.world.item.Items.*;

public class TreeMinerRecipeProvider extends RecipeProvider {
    public TreeMinerRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static final ImmutableList<ItemLike> COAL_List = ImmutableList.of(POD_COAL);
    public static final ImmutableList<ItemLike> COPPER_List = ImmutableList.of(POD_COPPER);
    public static final ImmutableList<ItemLike> DIAMOND_List = ImmutableList.of(POD_DIAMOND);
    public static final ImmutableList<ItemLike> EMERALD_List = ImmutableList.of(POD_EMERALD);
    public static final ImmutableList<ItemLike> GOLD_List = ImmutableList.of(POD_GOLD, NETHER_POD_GOLD);
    public static final ImmutableList<ItemLike> IRON_List = ImmutableList.of(POD_IRON);
    public static final ImmutableList<ItemLike> LAPIS_List = ImmutableList.of(POD_LAPIS);
    public static final ImmutableList<ItemLike> REDSTONE_List = ImmutableList.of(POD_REDSTONE);
    public static final ImmutableList<ItemLike> ANCIENT_DEBRIS_List = ImmutableList.of(NETHER_POD_ANCIENT_DEBRIS);
    public static final ImmutableList<ItemLike> GLOWSTONE_List = ImmutableList.of(NETHER_POD_GLOWSTONE);
    public static final ImmutableList<ItemLike> QUARTZ_List = ImmutableList.of(NETHER_POD_QUARTZ);

    @Override
    protected void buildRecipes() {
//        this.output.includeRootAdvancement();
//        createFruitSmelting(COAL_List, COAL, 0.1f);
//        createFruitSmelting(COPPER_List, COPPER_INGOT, 0.1f);
//        createFruitSmelting(DIAMOND_List, DIAMOND, 0.1f);
//        createFruitSmelting(EMERALD_List, EMERALD, 0.1f);
//        createFruitSmelting(GOLD_List, GOLD_INGOT, 0.1f);
//        createFruitSmelting(IRON_List, IRON_INGOT, 0.1f);
//        createFruitSmelting(LAPIS_List, LAPIS_LAZULI, 0.1f);
//        createFruitSmelting(REDSTONE_List, REDSTONE, 0.1f);
//        createFruitSmelting(ANCIENT_DEBRIS_List, NETHERITE_SCRAP, 0.8f);
//        createFruitSmelting(GLOWSTONE_List, GLOWSTONE_DUST, 0.1f);
//        createFruitSmelting(QUARTZ_List, QUARTZ, 0.1f);
//
//        shapeless(RecipeCategory.REDSTONE, POD_REDSTONE, REDSTONE, 3);
//        shapeless(RecipeCategory.MISC, NETHER_POD_QUARTZ, QUARTZ, 3);
//        shapeless(RecipeCategory.MISC, POD_COAL, COAL, 2);
//        shapeless(RecipeCategory.MISC, NETHER_POD_GOLD, GOLD_NUGGET, 4);

        createSaplingsRecipe(tag(ItemTags.COAL_ORES), POD_COAL, 1);
        createSaplingsRecipe(tag(ItemTags.COPPER_ORES), POD_COPPER, 1);
        createSaplingsRecipe(tag(ItemTags.DIAMOND_ORES), POD_DIAMOND, 1);
        createSaplingsRecipe(tag(ItemTags.EMERALD_ORES), POD_EMERALD, 1);
        createSaplingsRecipe(tag(ItemTags.GOLD_ORES), POD_GOLD, 1);
        createSaplingsRecipe(tag(ItemTags.IRON_ORES), POD_IRON, 1);
        createSaplingsRecipe(tag(ItemTags.LAPIS_ORES), POD_LAPIS, 1);
        createSaplingsRecipe(tag(ItemTags.REDSTONE_ORES), POD_REDSTONE, 1);
        createSaplingsRecipeNether(Ingredient.of(Blocks.NETHER_GOLD_ORE), NETHER_POD_GOLD, 1);
        createSaplingsRecipeNether(Ingredient.of(Blocks.ANCIENT_DEBRIS), NETHER_POD_ANCIENT_DEBRIS, 1);
        createSaplingsRecipeNether(Ingredient.of(Blocks.GLOWSTONE), NETHER_POD_GLOWSTONE, 1);
        createSaplingsRecipeNether(Ingredient.of(Blocks.NETHER_QUARTZ_ORE), NETHER_POD_QUARTZ, 1);

        logs(TreeMinerBlocks.LODE_PLANKS.item(), TreeMinerBlocks.LODE_LOG.item());
        logs(TreeMinerBlocks.NETHER_LODE_PLANKS.item(), TreeMinerBlocks.NETHER_LODE_LOG.item());
    }

    protected void logs(ItemLike planks, ItemLike log) {
        this.shapeless(BUILDING_BLOCKS, planks, 4).requires(log);
        this.shapeless(BUILDING_BLOCKS, CRAFTING_TABLE, 4).requires(planks);
    }

    public void shapeless(RecipeCategory category, ItemLike input, ItemLike output, int count) {
        shapeless(category, output, count).requires(input);
    }

    public void createSaplingsRecipe(Ingredient input, ItemLike output, int count){
        createSaplingsRecipe(MISC, input, ItemTags.SAPLINGS, output, count);
    }

    public void createSaplingsRecipeNether(Ingredient input, ItemLike output, int count){
        createSaplingsRecipe(MISC, input, TreeMinerItemTags.ItemTags.FUNGUS, output, count);
    }
    public void createSaplingsRecipe(RecipeCategory category, Ingredient input, TagKey<Item> input1, ItemLike output, int count) {
        this.shaped(category, output, count)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('B', input1)
                .define('A', input);
    }

    public void createFruitSmelting(List<ItemLike> ingredients, ItemLike output, float experience) {
        createFruitSmeltingFurnace(MISC, ingredients, output, 200, experience);
    }

    public void createFruitSmelting(RecipeCategory category, List<ItemLike> ingredients, ItemLike output, int time, float experience) {
        oreSmelting(ingredients, category, output, experience, time, "_fruit_smelting");
        oreBlasting(ingredients, category, output, experience, time/2, "_fruit_smelting");
    }

    public void createFruitSmeltingFurnace(RecipeCategory category, List<ItemLike> ingredients, ItemLike output, int time, float experience){
        oreSmelting(ingredients, category, output, experience, time, "_fruit_smelting");
    }

    public void createFruitSmeltingBlast(RecipeCategory category, List<ItemLike> ingredients, ItemLike output, int time, float experience) {
        oreBlasting(ingredients, category, output, experience, time, "_fruit_smelting");
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

        @Override
        public RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new TreeMinerRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "TreeMiner Recipes";
        }
    }
}
