package ctn.tree_miner.create;

import ctn.tree_miner.create.template.TreeMinerCreate;
import ctn.tree_miner.server.blocks.CrystalLodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import ctn.tree_miner.server.blocks.sapling.LodeSaplingBlock;
import ctn.tree_miner.server.blocks.sapling.TreeMinerTreeGrower;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.CreateBlockAPI;
import xiao_jin.api.create.template.ModBlockBehaviour;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.server.blocks.sapling.LodeSaplingBlock.SaplingProperties.saplingProperties;

/**
 * @author 尽
 * @apiNote 创建方块
 */
public class TreeMinerBlocks extends CreateBlockAPI {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final TreeMinerCreate LODE_LOG = new TreeMinerCreate("lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_PLANKS = new TreeMinerCreate("lode_planks", Block::new, createPlanks(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate NETHER_LODE_LOG = new TreeMinerCreate("nether_lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.NETHER, SoundType.NETHERRACK, 3.0F));
    public static final TreeMinerCreate NETHER_LODE_PLANKS = new TreeMinerCreate("nether_lode_planks", Block::new, createPlanks(MapColor.NETHER, SoundType.NETHERRACK , 3.0F));
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

    public static final TreeMinerCreate LODE_SAPLING_COAL =
            new TreeMinerCreate("lode_sapling_coal", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COAL, block, saplingProperties().growable(BlockTags.COAL_ORES)), createLeaves(MapColor.PLANT, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_IRON =
            new TreeMinerCreate("lode_sapling_iron", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_IRON, block, saplingProperties().growable(BlockTags.IRON_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_COPPER =
            new TreeMinerCreate("lode_sapling_copper", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COPPER, block, saplingProperties().growable(BlockTags.COPPER_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_LAPIS =
            new TreeMinerCreate("lode_sapling_lapis", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_LAPIS, block, saplingProperties().growable(BlockTags.LAPIS_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_EMERALD =
            new TreeMinerCreate("lode_sapling_emerald", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_EMERALD, block, saplingProperties().growable(BlockTags.EMERALD_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_GOLD =
            new TreeMinerCreate("lode_sapling_gold", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_GOLD, block, saplingProperties().growable(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_REDSTONE =
            new TreeMinerCreate("lode_sapling_redstone", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_REDSTONE, block, saplingProperties().growable(BlockTags.REDSTONE_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_DIAMOND =
            new TreeMinerCreate("lode_sapling_diamond", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.LODE_DIAMOND, block, saplingProperties().growable(BlockTags.DIAMOND_ORES)), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_QUARTZ =
            new TreeMinerCreate("nether_lode_sapling_quartz", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_QUARTZ, block, saplingProperties().growable(Blocks.NETHER_QUARTZ_ORE)), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GLOWSTONE =
            new TreeMinerCreate("nether_lode_sapling_glowstone", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GLOWSTONE, block, saplingProperties().growable(Blocks.GLOWSTONE)), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_ANCIENT_DEBRIS =
            new TreeMinerCreate("nether_lode_sapling_ancient_debris", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_ANCIENT_DEBRIS, block, saplingProperties().growable(Blocks.ANCIENT_DEBRIS)), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GOLD =
            new TreeMinerCreate("nether_lode_sapling_gold", (block) ->
            new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GOLD, block, saplingProperties().growable(Blocks.NETHER_GOLD_ORE)), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength).requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createPlanks(MapColor color, SoundType sound, float hardness){
        return ModBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(hardness)
                .sound(sound).requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createLeaves(MapColor color, SoundType sound, float hardness){
        return ModBlockBehaviour.Properties.of()
                .mapColor(color)
                .randomTicks()
                .instabreak()
                .sound(sound)
                .pushReaction(PushReaction.DESTROY)
                .strength(hardness)
                .noOcclusion()
                .noCollission();

    }
}
