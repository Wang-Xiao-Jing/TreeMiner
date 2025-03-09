package ctn.tree_miner.datagen;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xiao_jin.api.datagen.ModelProviderAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;
import static ctn.tree_miner.create.TreeMinerItems.*;
import static ctn.tree_miner.datagen.TreeMinerModels.CROSS;
import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;

/**
 * 数据生成器-模型
 */
public class ModelProvider extends ModelProviderAPI {
    public ModelProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    /**
     * 注册模型
     */
    @Override
    protected void registerModels(BlockModelGenerators blockModels,  ItemModelGenerators itemModels) {
        createRotatedPillar(blockModels, TreeMinerBlocks.LODE_LOG.block());
        createRotatedPillar(blockModels, TreeMinerBlocks.NETHER_LODE_LOG.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_COAL.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_IRON.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_COPPER.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_LAPIS.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_EMERALD.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_GOLD.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_REDSTONE.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES_DIAMOND.block());
        createRegular(blockModels, TreeMinerBlocks.NETHER_LODE_LEAVES_QUARTZ.block());
        createRegular(blockModels, TreeMinerBlocks.NETHER_LODE_LEAVES_GLOWSTONE.block());
        createRegular(blockModels, TreeMinerBlocks.NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block());
        createRegular(blockModels, TreeMinerBlocks.NETHER_LODE_LEAVES_GOLD.block());
        createRegular(blockModels, TreeMinerBlocks.LODE_PLANKS.block());
        createRegular(blockModels, TreeMinerBlocks.NETHER_LODE_PLANKS.block());
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_COAL.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_IRON.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_COPPER.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_LAPIS.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_EMERALD.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_GOLD.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_REDSTONE.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, LODE_SAPLING_DIAMOND.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, NETHER_LODE_SAPLING_QUARTZ.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, NETHER_LODE_SAPLING_GLOWSTONE.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockModels, NETHER_LODE_SAPLING_GOLD.block(), BlockModelGenerators.PlantType.NOT_TINTED);

        CreateRegularItemModel(itemModels, POD_COAL.get());
        CreateRegularItemModel(itemModels, POD_COPPER.get());
        CreateRegularItemModel(itemModels, POD_DIAMOND.get());
        CreateRegularItemModel(itemModels, POD_EMERALD.get());
        CreateRegularItemModel(itemModels, POD_GOLD.get());
        CreateRegularItemModel(itemModels, POD_IRON.get());
        CreateRegularItemModel(itemModels, POD_LAPIS.get());
        CreateRegularItemModel(itemModels, POD_REDSTONE.get());
        CreateRegularItemModel(itemModels, NETHER_POD_ANCIENT_DEBRIS.get());
        CreateRegularItemModel(itemModels, NETHER_POD_GLOWSTONE.get());
        CreateRegularItemModel(itemModels, NETHER_POD_GOLD.get());
        CreateRegularItemModel(itemModels, NETHER_POD_QUARTZ.get());
//        CreateRegularItemModel(itemModels, LODE_SAPLING.Item());
    }

    public void createCrossBlockWithDefaultItem(BlockModelGenerators blockModels, Block block, BlockModelGenerators.PlantType plantType) {
        blockModels.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockModels, block));
        createCrossBlock(blockModels,block, plantType);
    }

    public void createCrossBlock(BlockModelGenerators blockModels, Block block, BlockModelGenerators.PlantType plantType) {
        TextureMapping texturemapping = plantType.getTextureMapping(block);
        this.createCrossBlock(blockModels,block, plantType, texturemapping);
    }

    public void createCrossBlock(BlockModelGenerators blockModels, Block block, BlockModelGenerators.PlantType plantType, TextureMapping textureMapping) {
        ResourceLocation resourcelocation = CROSS.create(block, textureMapping, blockModels.modelOutput);
        blockModels.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
    }

    public static void createCustom(BlockModelGenerators blockModels,ItemModelGenerators itemModels,
                                    Block block, TexturedModel model) {
//        CreateCustomBlockModel(blockModels);
//        CreateCustomItemModel(itemModels);
    }

    public static void CreateCustomBlockModel(BlockModelGenerators blockModels, Block block, ModelTemplate model) {
//        blockModels.createTrivialBlock(block, model);
    }

    public static void CreateCustomItemModel(ItemModelGenerators itemModels, Item item, ModelTemplate model) {
        itemModels.generateFlatItem(item, model);
    }

}
