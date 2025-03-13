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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import xiao_jin.api.datagen.XiaoJinModelProvider;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;
import static ctn.tree_miner.create.TreeMinerItems.*;
import static ctn.tree_miner.datagen.TreeMinerModels.CROSS;
import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;

/**
 * 数据生成器-模型
 */
public class TreeMinerModelProvider extends XiaoJinModelProvider {
    public TreeMinerModelProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    /**
     * 注册模型
     */
    @Override
    protected void registerModels(BlockModelGenerators blockGenerators,  ItemModelGenerators itemGenerators) {
        createRotatedPillar(blockGenerators, TreeMinerBlocks.LODE_LOG.block());
        createRotatedPillar(blockGenerators, TreeMinerBlocks.NETHER_LODE_LOG.block());

        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_COAL.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_IRON.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_COPPER.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_LAPIS.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_EMERALD.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_GOLD.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_REDSTONE.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_LEAVES_DIAMOND.block());
        createRegular(blockGenerators, TreeMinerBlocks.NETHER_LODE_LEAVES_QUARTZ.block());
        createRegular(blockGenerators, TreeMinerBlocks.NETHER_LODE_LEAVES_GLOWSTONE.block());
        createRegular(blockGenerators, TreeMinerBlocks.NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block());
        createRegular(blockGenerators, TreeMinerBlocks.NETHER_LODE_LEAVES_GOLD.block());
        createRegular(blockGenerators, TreeMinerBlocks.LODE_PLANKS.block());
        createRegular(blockGenerators, TreeMinerBlocks.NETHER_LODE_PLANKS.block());

        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_COAL.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_IRON.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_COPPER.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_LAPIS.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_EMERALD.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_GOLD.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_REDSTONE.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, LODE_SAPLING_DIAMOND.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, NETHER_LODE_SAPLING_QUARTZ.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, NETHER_LODE_SAPLING_GLOWSTONE.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block(), BlockModelGenerators.PlantType.NOT_TINTED);
        createCrossBlockWithDefaultItem(blockGenerators, NETHER_LODE_SAPLING_GOLD.block(), BlockModelGenerators.PlantType.NOT_TINTED);

        createRegulaItemModel(itemGenerators, POD_COAL.get());
        createRegulaItemModel(itemGenerators, POD_COPPER.get());
        createRegulaItemModel(itemGenerators, POD_DIAMOND.get());
        createRegulaItemModel(itemGenerators, POD_EMERALD.get());
        createRegulaItemModel(itemGenerators, POD_GOLD.get());
        createRegulaItemModel(itemGenerators, POD_IRON.get());
        createRegulaItemModel(itemGenerators, POD_LAPIS.get());
        createRegulaItemModel(itemGenerators, POD_REDSTONE.get());
        createRegulaItemModel(itemGenerators, NETHER_POD_ANCIENT_DEBRIS.get());
        createRegulaItemModel(itemGenerators, NETHER_POD_GLOWSTONE.get());
        createRegulaItemModel(itemGenerators, NETHER_POD_GOLD.get());
        createRegulaItemModel(itemGenerators, NETHER_POD_QUARTZ.get());

        createLodeFruit(blockGenerators, TEST_FRUIT_MATURE.get());
    }

    public void createLodeFruit(BlockModelGenerators blockModelsGenerators, Block cropBlock){
        blockModelsGenerators.createCropBlock(cropBlock, STAGE_3, 0, 0, 0, 3);
    }

    public void createCrossBlockWithDefaultItem(BlockModelGenerators blockGenerators, Block block, BlockModelGenerators.PlantType plantType) {
        blockGenerators.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockGenerators, block));
        createCrossBlock(blockGenerators,block, plantType);
    }

    public void createCrossBlock(BlockModelGenerators blockGenerators, Block block, BlockModelGenerators.PlantType plantType) {
        TextureMapping texturemapping = plantType.getTextureMapping(block);
        this.createCrossBlock(blockGenerators,block, texturemapping);
    }

    public void createCrossBlock(BlockModelGenerators blockGenerators, Block block,TextureMapping textureMapping) {
        ResourceLocation resourcelocation = CROSS.create(block, textureMapping, blockGenerators.modelOutput);
        blockGenerators.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
    }
}
