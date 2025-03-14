package ctn.tree_miner.create;

import ctn.tree_miner.api.TreeMinerCreate;
import ctn.tree_miner.server.blocks.CrystalLodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeFruitBlock;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeSaplingBlock;
import ctn.tree_miner.server.TreeMinerTreeGrower;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.XiaoJinCreateBlock;
import xiao_jin.api.create.XiaoJinBlockBehaviour;
import xiao_jin.api.create.XiaoJinCreate;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.server.blocks.LodeSaplingBlock.SaplingProperties.saplingProperties;

/**
 * @author 尽
 * @apiNote 创建方块
 */
public class TreeMinerBlocks extends XiaoJinCreateBlock {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final TreeMinerCreate LODE_LOG = new TreeMinerCreate("lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_PLANKS = new TreeMinerCreate("lode_planks", Block::new, createProperties(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate NETHER_LODE_LOG = new TreeMinerCreate("nether_lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.NETHER, SoundType.NETHERRACK, 3.0F));
    public static final TreeMinerCreate NETHER_LODE_PLANKS = new TreeMinerCreate("nether_lode_planks", Block::new, createProperties(MapColor.NETHER, SoundType.NETHERRACK , 3.0F));

    public static final TreeMinerCreate LODE_LEAVES_COAL = new TreeMinerCreate("lode_leaves_coal", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_IRON = new TreeMinerCreate("lode_leaves_iron", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 4.0F));
    public static final TreeMinerCreate LODE_LEAVES_COPPER = new TreeMinerCreate("lode_leaves_copper", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_LAPIS = new TreeMinerCreate("lode_leaves_lapis", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_REDSTONE = new TreeMinerCreate("lode_leaves_redstone", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_GOLD = new TreeMinerCreate("lode_leaves_gold", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 8.0F));
    public static final TreeMinerCreate LODE_LEAVES_EMERALD = new TreeMinerCreate("lode_leaves_emerald", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 10.0F));
    public static final TreeMinerCreate LODE_LEAVES_DIAMOND = new TreeMinerCreate("lode_leaves_diamond", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 10.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GLOWSTONE = new TreeMinerCreate("nether_lode_leaves_glowstone", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.SAND, SoundType.GLASS, 2.2f).lightLevel(blockState -> 15));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_QUARTZ = new TreeMinerCreate("nether_lode_leaves_quartz", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHER_ORE, 4.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GOLD = new TreeMinerCreate("nether_lode_leaves_gold", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHER_GOLD_ORE, 4.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_leaves_ancient_debris", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.ANCIENT_DEBRIS, 30.0F, 1200.0F));

    public static final TreeMinerCreate LODE_SAPLING_COAL = new TreeMinerCreate("lode_sapling_coal", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COAL, block, saplingProperties().growable(BlockTags.COAL_ORES)), createSaplingProperties(MapColor.PLANT, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_IRON = new TreeMinerCreate("lode_sapling_iron", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_IRON, block, saplingProperties().growable(BlockTags.IRON_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_COPPER = new TreeMinerCreate("lode_sapling_copper", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COPPER, block, saplingProperties().growable(BlockTags.COPPER_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_LAPIS = new TreeMinerCreate("lode_sapling_lapis", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_LAPIS, block, saplingProperties().growable(BlockTags.LAPIS_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_EMERALD = new TreeMinerCreate("lode_sapling_emerald", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_EMERALD, block, saplingProperties().growable(BlockTags.EMERALD_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_GOLD = new TreeMinerCreate("lode_sapling_gold", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_GOLD, block, saplingProperties().growable(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_REDSTONE = new TreeMinerCreate("lode_sapling_redstone", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_REDSTONE, block, saplingProperties().growable(BlockTags.REDSTONE_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_DIAMOND = new TreeMinerCreate("lode_sapling_diamond", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_DIAMOND, block, saplingProperties().growable(BlockTags.DIAMOND_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_QUARTZ = new TreeMinerCreate("nether_lode_sapling_quartz", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_QUARTZ, block, saplingProperties().growable(Blocks.NETHER_QUARTZ_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GLOWSTONE = new TreeMinerCreate("nether_lode_sapling_glowstone", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GLOWSTONE, block, saplingProperties().growable(Blocks.GLOWSTONE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_sapling_ancient_debris", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_ANCIENT_DEBRIS, block, saplingProperties().growable(Blocks.ANCIENT_DEBRIS)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GOLD = new TreeMinerCreate("nether_lode_sapling_gold", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GOLD, block, saplingProperties().growable(Blocks.NETHER_GOLD_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));

    public static final DeferredBlock<Block> LODE_FRUIT_COAL = XiaoJinCreate.createBlock("lode_fruit_coal", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_IRON = XiaoJinCreate.createBlock("lode_fruit_iron", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_COPPER = XiaoJinCreate.createBlock("lode_fruit_copper", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_LAPIS = XiaoJinCreate.createBlock("lode_fruit_lapis", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_EMERALD = XiaoJinCreate.createBlock("lode_fruit_emerald", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_GOLD = XiaoJinCreate.createBlock("lode_fruit_gold", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_REDSTONE = XiaoJinCreate.createBlock("lode_fruit_redstone", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> LODE_FRUIT_DIAMOND = XiaoJinCreate.createBlock("lode_fruit_diamond", LodeFruitBlock::new, createFruitProperties(SoundType.STONE), BLOCKS);
    public static final DeferredBlock<Block> NETHER_LODE_FRUIT_QUARTZ = XiaoJinCreate.createBlock("nether_lode_fruit_quartz", LodeFruitBlock::new, createFruitProperties(SoundType.NETHERRACK), BLOCKS);
    public static final DeferredBlock<Block> NETHER_LODE_FRUIT_GLOWSTONE = XiaoJinCreate.createBlock("nether_lode_fruit_glowstone", LodeFruitBlock::new, createFruitProperties(SoundType.NETHERRACK), BLOCKS);
    public static final DeferredBlock<Block> NETHER_LODE_FRUIT_ANCIENT_DEBRIS = XiaoJinCreate.createBlock("nether_lode_fruit_debris", LodeFruitBlock::new, createFruitProperties(SoundType.NETHERRACK), BLOCKS);
    public static final DeferredBlock<Block> NETHER_LODE_FRUIT_GOLD = XiaoJinCreate.createBlock("nether_lode_fruit_gold", LodeFruitBlock::new, createFruitProperties(SoundType.NETHERRACK), BLOCKS);

    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createFruitProperties(SoundType sound){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(0.3f)
                .sound(sound)
                .randomTicks()
                .noOcclusion()
                .noCollission()
                .isValidSpawn(Blocks::ocelotOrParrot)
                .isSuffocating(XiaoJinCreateBlock::never)
                .isViewBlocking(XiaoJinCreateBlock::never)
                .pushReaction(PushReaction.DESTROY)
                .isRedstoneConductor(XiaoJinCreateBlock::never);
    }

    public static BlockBehaviour.Properties createProperties(MapColor color, SoundType sound, float hardness){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(hardness)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createSaplingProperties(MapColor color, SoundType sound){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .randomTicks()
                .instabreak()
                .sound(sound)
                .pushReaction(PushReaction.DESTROY)
                .strength(0.3f)
                .noOcclusion()
                .noCollission();
    }

    public static final IntegerProperty STAGE_2 = IntegerProperty.create("stage", 0, 2);
    public static final IntegerProperty STAGE_3 = IntegerProperty.create("stage", 0, 3);
    public static final IntegerProperty STAGE_4 = IntegerProperty.create("stage", 0, 4);
}
