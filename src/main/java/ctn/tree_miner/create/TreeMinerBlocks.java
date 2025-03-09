package ctn.tree_miner.create;

import ctn.tree_miner.create.template.TreeMinerCreate;
import ctn.tree_miner.server.blocks.CrystalLodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.CreateBlockAPI;
import xiao_jin.api.create.template.ModBlockBehaviour;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static xiao_jin.api.create.template.ModCreateAPI.createBlock;
import static xiao_jin.api.create.template.ModCreateAPI.createBlockItem;

/**
 * @author 尽
 * @apiNote 创建方块
 */
public class TreeMinerBlocks extends CreateBlockAPI {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final TreeMinerCreate LODE_LOG = new TreeMinerCreate("lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate NETHER_LODE_LOG = new TreeMinerCreate("nether_lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_PLANKS = new TreeMinerCreate("lode_planks", Block::new, createPlanks(MapColor.STONE, SoundType.STONE , 2.2f));
    public static final TreeMinerCreate NETHER_LODE_PLANKS = new TreeMinerCreate("nether_lode_planks", Block::new, createPlanks(MapColor.STONE, SoundType.STONE , 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_COAL = new TreeMinerCreate("lode_leaves_coal", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_IRON = new TreeMinerCreate("lode_leaves_iron", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_COPPER = new TreeMinerCreate("lode_leaves_copper", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_LAPIS = new TreeMinerCreate("lode_leaves_lapis", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_EMERALD = new TreeMinerCreate("lode_leaves_emerald", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_GOLD = new TreeMinerCreate("lode_leaves_gold", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_REDSTONE = new TreeMinerCreate("lode_leaves_redstone", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate LODE_LEAVES_DIAMOND = new TreeMinerCreate("lode_leaves_diamond", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_QUARTZ = new TreeMinerCreate("nether_lode_leaves_quartz", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHERRACK, 2.2f));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GLOWSTONE = new TreeMinerCreate("nether_lode_leaves_glowstone", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHERRACK, 2.2f));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_leaves_ancient_debris", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHERRACK, 2.2f));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GOLD = new TreeMinerCreate("nether_lode_leaves_gold", CrystalLodeLeavesBlock::new, leavesProperties(MapColor.NETHER, SoundType.NETHERRACK, 2.2f));
    public static final TreeMinerCreate LODE_SAPLING_COAL = new TreeMinerCreate("lode_sapling_coal", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.PLANT, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_IRON = new TreeMinerCreate("lode_sapling_iron", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_COPPER = new TreeMinerCreate("lode_sapling_copper", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_LAPIS = new TreeMinerCreate("lode_sapling_lapis", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_EMERALD = new TreeMinerCreate("lode_sapling_emerald", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_GOLD = new TreeMinerCreate("lode_sapling_gold", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_REDSTONE = new TreeMinerCreate("lode_sapling_redstone", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate LODE_SAPLING_DIAMOND = new TreeMinerCreate("lode_sapling_diamond", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.STONE, SoundType.STONE, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_QUARTZ = new TreeMinerCreate("nether_lode_sapling_quartz", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GLOWSTONE = new TreeMinerCreate("nether_lode_sapling_glowstone", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_sapling_ancient_debris", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GOLD = new TreeMinerCreate("nether_lode_sapling_gold", block -> new SaplingBlock(TreeGrower.OAK, block), createLeaves(MapColor.NETHER, SoundType.NETHERRACK, 1));

    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength);
    }

    public static BlockBehaviour.Properties createPlanks(MapColor color, SoundType sound, float hardness){
        return ModBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(hardness)
                .sound(sound);
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
