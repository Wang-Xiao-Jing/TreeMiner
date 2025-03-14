package ctn.tree_miner.datagen;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.Property;
import xiao_jin.api.datagen.XiaoJinModelProvider;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;
import static ctn.tree_miner.create.TreeMinerItems.*;
import static ctn.tree_miner.datagen.TreeMinerModels.CROSS;
import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.model.TextureMapping.defaultTexture;

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
            createRotatedPillar(LODE_LOG.block());
            createRotatedPillar(NETHER_LODE_LOG.block());

            createRegular(LODE_LEAVES_COAL.block());
            createRegular(LODE_LEAVES_IRON.block());
            createRegular(LODE_LEAVES_COPPER.block());
            createRegular(LODE_LEAVES_LAPIS.block());
            createRegular(LODE_LEAVES_EMERALD.block());
            createRegular(LODE_LEAVES_GOLD.block());
            createRegular(LODE_LEAVES_REDSTONE.block());
            createRegular(LODE_LEAVES_DIAMOND.block());
            createRegular(NETHER_LODE_LEAVES_QUARTZ.block());
            createRegular(NETHER_LODE_LEAVES_GLOWSTONE.block());
            createRegular(NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block());
            createRegular(NETHER_LODE_LEAVES_GOLD.block());
            createRegular(LODE_PLANKS.block());
            createRegular(NETHER_LODE_PLANKS.block());

            createSaplingItem(LODE_SAPLING_COAL.block());
            createSaplingItem(LODE_SAPLING_IRON.block());
            createSaplingItem(LODE_SAPLING_COPPER.block());
            createSaplingItem(LODE_SAPLING_LAPIS.block());
            createSaplingItem(LODE_SAPLING_EMERALD.block());
            createSaplingItem(LODE_SAPLING_GOLD.block());
            createSaplingItem(LODE_SAPLING_REDSTONE.block());
            createSaplingItem(LODE_SAPLING_DIAMOND.block());
            createSaplingItem(NETHER_LODE_SAPLING_QUARTZ.block());
            createSaplingItem(NETHER_LODE_SAPLING_GLOWSTONE.block());
            createSaplingItem(NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block());
            createSaplingItem(NETHER_LODE_SAPLING_GOLD.block());

            createRegulaItemModel(POD_COAL.get());
            createRegulaItemModel(POD_IRON.get());
            createRegulaItemModel(POD_COPPER.get());
            createRegulaItemModel(POD_LAPIS.get());
            createRegulaItemModel(POD_EMERALD.get());
            createRegulaItemModel(POD_GOLD.get());
            createRegulaItemModel(POD_REDSTONE.get());
            createRegulaItemModel(POD_DIAMOND.get());
            createRegulaItemModel(NETHER_POD_QUARTZ.get());
            createRegulaItemModel(NETHER_POD_GLOWSTONE.get());
            createRegulaItemModel(NETHER_POD_ANCIENT_DEBRIS.get());
            createRegulaItemModel(NETHER_POD_GOLD.get());

            createFruitBlockState(LODE_FRUIT_COAL.get());
            createFruitBlockState(LODE_FRUIT_IRON.get());
            createFruitBlockState(LODE_FRUIT_COPPER.get());
            createFruitBlockState(LODE_FRUIT_LAPIS.get());
            createFruitBlockState(LODE_FRUIT_EMERALD.get());
            createFruitBlockState(LODE_FRUIT_GOLD.get());
            createFruitBlockState(LODE_FRUIT_REDSTONE.get());
            createFruitBlockState(LODE_FRUIT_DIAMOND.get());
            createFruitBlockState(NETHER_LODE_FRUIT_QUARTZ.get());
            createFruitBlockState(NETHER_LODE_FRUIT_GLOWSTONE.get());
            createFruitBlockState(NETHER_LODE_FRUIT_ANCIENT_DEBRIS.get());
            createFruitBlockState(NETHER_LODE_FRUIT_GOLD.get());
        }

        public void createFruitBlockState(Block block) {
            createCropBlock(block, STAGE_3, 0, 0, 0, 3);
        }

        public void createCropBlock(Block cropBlock, Property<Integer> ageProperty, int... ageToVisualStageMapping) {
            if (ageProperty.getPossibleValues().size() != ageToVisualStageMapping.length) {
                throw new IllegalArgumentException();
            } else {
                Int2ObjectMap<ResourceLocation> int2objectmap = new Int2ObjectOpenHashMap<>();
                PropertyDispatch propertydispatch = PropertyDispatch.property(ageProperty)
                        .generate(
                                p_388091_ -> {
                                    int i = ageToVisualStageMapping[p_388091_];
                                    ResourceLocation resourcelocation = int2objectmap.computeIfAbsent(
                                            i, p_387534_ -> blockGenerators.createSuffixedVariant(cropBlock, "_stage" + i, ModelTemplates.CROP, TextureMapping::crop)
                                    );
                                    return Variant.variant().with(VariantProperties.MODEL, resourcelocation);
                                }
                        );
//                blockGenerators.registerSimpleFlatItemModel(cropBlock.asItem());
                blockGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(cropBlock).with(propertydispatch));
            }
        }

        /**
         * 创建普通物品模型
         */
        public void createRegulaItemModel(Item item) {
            XiaoJinModelProvider.createRegulaItemModel(itemGenerators, item);
        }

        /**
         * 创建普通模型
         * <p>完整六面模型 方块和物品
         */
        public void createRegular(Block block) {
            XiaoJinModelProvider.createRegular(blockGenerators,block);
        }

        /**
         * 创建原木类方块模型
         */
        public void createRotatedPillar(Block block) {
            XiaoJinModelProvider.createRotatedPillar(blockGenerators, block);
        }

        public void createSaplingItem(Block block){
            createSaplingItem(block, BlockModelGenerators.PlantType.NOT_TINTED);
        }

        public void createSaplingItem(Block block, BlockModelGenerators.PlantType plantType) {
            blockGenerators.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockGenerators, block));
            createCrossBlock(block, plantType);
        }

        public void createCrossBlock(Block block, BlockModelGenerators.PlantType plantType) {
            TextureMapping texturemapping = plantType.getTextureMapping(block);
            this.createCrossBlock(block, texturemapping);
        }

        public void createCrossBlock(Block block,TextureMapping textureMapping) {
            ResourceLocation resourcelocation = CROSS.create(block, textureMapping, blockGenerators.modelOutput);
            blockGenerators.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
        }
    }
}
