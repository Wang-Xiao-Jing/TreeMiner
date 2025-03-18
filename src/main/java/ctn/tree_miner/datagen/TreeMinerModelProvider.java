package ctn.tree_miner.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
        CreateModels createModels = new CreateModels(blockGenerators, itemGenerators);
        createModels.create();
    }

    public static class CreateModels{
        BlockModelGenerators blockGenerators;
        ItemModelGenerators itemGenerators;
        public CreateModels(BlockModelGenerators blockGenerators, ItemModelGenerators itemGenerators){
            this.blockGenerators = blockGenerators;
            this.itemGenerators = itemGenerators;
        }

        public void create(){
            createRotatedPillarModel(LODE_LOG.block());
            createRotatedPillarModel(NETHER_LODE_LOG.block());

            createRegularModel(LODE_PLANKS.block());
            createFamily(LODE_PLANKS.block())
                    .slab(LODE_SLAB.block())
                    .stairs(LODE_STAIR.block())
                    .fence(LODE_FENCE.block())
                    .fenceGate(LODE_FENCE_GATE.block());
//            createSlab(LODE_SLAB.block());
//            createFamily(LODE_STAIR.block());
//            createRegularModel(LODE_FENCE.block());
//            createRegularModel(LODE_FENCE_GATE.block());

            createRegularModel(NETHER_LODE_PLANKS.block());
            createFamily(NETHER_LODE_PLANKS.block())
                    .slab(NETHER_LODE_SLAB.block())
                    .stairs(NETHER_LODE_STAIR.block())
                    .fence(NETHER_LODE_FENCE.block())
                    .fenceGate(NETHER_LODE_FENCE_GATE.block());

//            createSlab(NETHER_LODE_SLAB.block());
//            createRegularModel(NETHER_LODE_STAIR.block());
//            createRegularModel(NETHER_LODE_FENCE.block());
//            createRegularModel(NETHER_LODE_FENCE_GATE.block());

            createLeavesModel(LODE_LEAVES_COAL.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_IRON.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_COPPER.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_LAPIS.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_EMERALD.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_GOLD.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_REDSTONE.block(),"lode_leaves");
            createLeavesModel(LODE_LEAVES_DIAMOND.block(), "lode_leaves");

            createLeavesModel(NETHER_LODE_LEAVES_QUARTZ.block(),"nether_lode_leaves");
            createLeavesModel(NETHER_LODE_LEAVES_GLOWSTONE.block(), "nether_lode_leaves");
            createLeavesModel(NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block(), "nether_lode_leaves_ancient_debris1", "nether_lode_leaves_ancient_debris");
            createLeavesModel(NETHER_LODE_LEAVES_GOLD.block(),"nether_lode_leaves");

            createSaplingModel(LODE_SAPLING_COAL.block());
            createSaplingModel(LODE_SAPLING_IRON.block());
            createSaplingModel(LODE_SAPLING_COPPER.block());
            createSaplingModel(LODE_SAPLING_LAPIS.block());
            createSaplingModel(LODE_SAPLING_EMERALD.block());
            createSaplingModel(LODE_SAPLING_GOLD.block());
            createSaplingModel(LODE_SAPLING_REDSTONE.block());
            createSaplingModel(LODE_SAPLING_DIAMOND.block());

            createSaplingModel(NETHER_LODE_SAPLING_QUARTZ.block());
            createSaplingModel(NETHER_LODE_SAPLING_GLOWSTONE.block());
            createSaplingModel(NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block());
            createSaplingModel(NETHER_LODE_SAPLING_GOLD.block());

            createItemModel(POD_COAL.get());
            createItemModel(POD_IRON.get());
            createItemModel(POD_COPPER.get());
            createItemModel(POD_LAPIS.get());
            createItemModel(POD_EMERALD.get());
            createItemModel(POD_GOLD.get());
            createItemModel(POD_REDSTONE.get());
            createItemModel(POD_DIAMOND.get());

            createItemModel(NETHER_POD_QUARTZ.get());
            createItemModel(NETHER_POD_GLOWSTONE.get());
            createItemModel(NETHER_POD_ANCIENT_DEBRIS.get());
            createItemModel(NETHER_POD_GOLD.get());
        }

        /** 创建多变种方块 */
        public BlockModelGenerators.BlockFamilyProvider createFamily(Block block){
            return blockGenerators.familyWithExistingFullBlock(block);
        }

        /**
         * 创建多形态树叶模型
         */
        public void createLeavesModel(Block block, String acquiesce) {
            blockGenerators.blockStateOutput.accept(createLeavesModel(block,
                    ModelLocationUtils.decorateBlockModelLocation(MOD_ID + ":" + acquiesce),
                    ModelLocationUtils.getModelLocation(block)));
        }

        /**
         * 创建多形态树叶模型
         */
        public void createLeavesModel(Block block, String acquiesce, String matureLocation) {
            blockGenerators.blockStateOutput.accept(createLeavesModel(block,
                    ModelLocationUtils.decorateBlockModelLocation(MOD_ID + ":" + acquiesce),
                    ModelLocationUtils.decorateBlockModelLocation(MOD_ID + ":" + matureLocation)));
        }

        public BlockStateGenerator createLeavesModel(Block block, ResourceLocation acquiesce, ResourceLocation matureLocation) {
            Item chestItem = block.asItem();
            ItemModel.Unbaked unbaked = ItemModelUtils.plainModel(TexturedModel.LEAVES.create(block, blockGenerators.modelOutput));
            itemGenerators.itemModelOutput.accept(chestItem, unbaked);
            return MultiVariantGenerator.multiVariant(block)
                .with(PropertyDispatch.property(STAGE_3)
                            .select(0, Variant.variant().with(VariantProperties.MODEL, acquiesce))
                            .select(1, Variant.variant().with(VariantProperties.MODEL, acquiesce))
                            .select(2, Variant.variant().with(VariantProperties.MODEL, acquiesce))
                            .select(3, Variant.variant().with(VariantProperties.MODEL, matureLocation))
                );
        }

        /**
         * 创建普通物品模型
         */
        public void createItemModel(Item item) {
            XiaoJinModelProvider.createRegulaItemModel(itemGenerators, item);
        }

        /**
         * 创建普通模型
         * <p>完整六面模型 方块和物品
         */
        public void createRegularModel(Block block) {
            XiaoJinModelProvider.createRegular(blockGenerators,block);
        }

        /**
         * 创建原木类方块模型
         */
        public void createRotatedPillarModel(Block block) {
            XiaoJinModelProvider.createRotatedPillar(blockGenerators, block);
        }

        /** 创建树苗模型 */
        public void createSaplingModel(Block block){
            createSaplingModel(block, BlockModelGenerators.PlantType.NOT_TINTED);
        }

        /** 创建树苗模型 */
        public void createSaplingModel(Block block, BlockModelGenerators.PlantType plantType) {
            blockGenerators.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockGenerators, block));
            createCrossBlock(block, plantType);
        }

        public void createCrossBlock(Block block, BlockModelGenerators.PlantType plantType) {
            TextureMapping texturemapping = plantType.getTextureMapping(block);
            this.createCrossBlock(block, texturemapping);
        }

        public void createCrossBlock(Block block, TextureMapping textureMapping) {
            ResourceLocation resourcelocation = CROSS.create(block, textureMapping, blockGenerators.modelOutput);
            blockGenerators.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
        }
    }
}
