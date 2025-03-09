package ctn.tree_miner.datagen;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.PackOutput;
import xiao_jin.api.datagen.ModelProviderAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.LODE_SAPLING;

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
    protected void registerModels( BlockModelGenerators blockModels,  ItemModelGenerators itemModels) {
        createRotatedPillar(blockModels, TreeMinerBlocks.LODE_LOG.Block());
        createRegular(blockModels, TreeMinerBlocks.LODE_LEAVES.Block());
        createRegular(blockModels, TreeMinerBlocks.LODE_PLANKS.Block());
        CreateRegularItemModel(itemModels, LODE_SAPLING.Item());
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(LODE_SAPLING.Block(),
                getResourceLocation(blockModels, TexturedModel.CUBE, LODE_SAPLING.Block())));
    }
}
