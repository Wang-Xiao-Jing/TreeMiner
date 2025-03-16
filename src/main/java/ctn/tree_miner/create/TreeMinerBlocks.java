package ctn.tree_miner.create;

import ctn.tree_miner.api.TreeMinerCreate;
import ctn.tree_miner.server.TreeMinerTreeGrower;
import ctn.tree_miner.server.blocks.CrystalLodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeSaplingBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.XiaoJinBlockBehaviour;
import xiao_jin.api.create.XiaoJinCreateBlock;

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

    public static final TreeMinerCreate LODE_LEAVES_COAL = new TreeMinerCreate("lode_leaves_coal", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_COAL), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_IRON = new TreeMinerCreate("lode_leaves_iron", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_IRON), leavesProperties(MapColor.STONE, SoundType.STONE, 4.0F));
    public static final TreeMinerCreate LODE_LEAVES_COPPER = new TreeMinerCreate("lode_leaves_copper", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_COPPER), leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_LAPIS = new TreeMinerCreate("lode_leaves_lapis", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_LAPIS), leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_REDSTONE = new TreeMinerCreate("lode_leaves_redstone", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_REDSTONE), leavesProperties(MapColor.STONE, SoundType.STONE, 5.0F));
    public static final TreeMinerCreate LODE_LEAVES_GOLD = new TreeMinerCreate("lode_leaves_gold", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_GOLD), leavesProperties(MapColor.STONE, SoundType.STONE, 8.0F));
    public static final TreeMinerCreate LODE_LEAVES_EMERALD = new TreeMinerCreate("lode_leaves_emerald", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_EMERALD), leavesProperties(MapColor.STONE, SoundType.STONE, 10.0F));
    public static final TreeMinerCreate LODE_LEAVES_DIAMOND = new TreeMinerCreate("lode_leaves_diamond", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_DIAMOND), leavesProperties(MapColor.STONE, SoundType.STONE, 10.0F));

    public static final TreeMinerCreate NETHER_LODE_LEAVES_GLOWSTONE = new TreeMinerCreate("nether_lode_leaves_glowstone", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.SAND, SoundType.GLASS, 2.2f).lightLevel(blockState -> 15));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_QUARTZ = new TreeMinerCreate("nether_lode_leaves_quartz", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHER_ORE, 4.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GOLD = new TreeMinerCreate("nether_lode_leaves_gold", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHER_GOLD_ORE, 4.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_leaves_ancient_debris", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.ANCIENT_DEBRIS, 30.0F, 1200.0F));

    public static final TreeMinerCreate LODE_SAPLING_COAL = new TreeMinerCreate("lode_sapling_coal", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COAL, properties, saplingProperties().placeable(BlockTags.COAL_ORES)), createSaplingProperties(MapColor.PLANT, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_IRON = new TreeMinerCreate("lode_sapling_iron", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_IRON, properties, saplingProperties().placeable(BlockTags.IRON_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_COPPER = new TreeMinerCreate("lode_sapling_copper", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COPPER, properties, saplingProperties().placeable(BlockTags.COPPER_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_LAPIS = new TreeMinerCreate("lode_sapling_lapis", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_LAPIS, properties, saplingProperties().placeable(BlockTags.LAPIS_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_EMERALD = new TreeMinerCreate("lode_sapling_emerald", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_EMERALD, properties, saplingProperties().placeable(BlockTags.EMERALD_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_GOLD = new TreeMinerCreate("lode_sapling_gold", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_GOLD, properties, saplingProperties().placeable(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_REDSTONE = new TreeMinerCreate("lode_sapling_redstone", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_REDSTONE, properties, saplingProperties().placeable(BlockTags.REDSTONE_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_DIAMOND = new TreeMinerCreate("lode_sapling_diamond", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_DIAMOND, properties, saplingProperties().placeable(BlockTags.DIAMOND_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GLOWSTONE = new TreeMinerCreate("nether_lode_sapling_glowstone", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GLOWSTONE, properties, saplingProperties().placeable(Blocks.GLOWSTONE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_QUARTZ = new TreeMinerCreate("nether_lode_sapling_quartz", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_QUARTZ, properties, saplingProperties().placeable(Blocks.NETHER_QUARTZ_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GOLD = new TreeMinerCreate("nether_lode_sapling_gold", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GOLD, properties, saplingProperties().placeable(Blocks.NETHER_GOLD_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_sapling_ancient_debris", (properties) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_ANCIENT_DEBRIS, properties, saplingProperties().placeable(Blocks.ANCIENT_DEBRIS)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK), new Item.Properties().fireResistant());

    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength)
                .requiresCorrectToolForDrops();
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
                .sound(sound)
                .pushReaction(PushReaction.DESTROY)
                .strength(0.3f)
                .noOcclusion().noCollission().randomTicks().instabreak();
    }

    public static final IntegerProperty STAGE_3 = IntegerProperty.create("stage", 0, 3);
}
