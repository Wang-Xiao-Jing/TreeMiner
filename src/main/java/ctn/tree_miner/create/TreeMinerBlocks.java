package ctn.tree_miner.create;

import ctn.tree_miner.create.template.ModCreate;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
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

    public static final ModCreate LODE_LOG = new ModCreate("lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final ModCreate LODE_LEAVES = new ModCreate("lode_leaves", LodeLeavesBlock::new, leavesProperties(MapColor.STONE, SoundType.STONE, 2.2f));
    public static final ModCreate LODE_PLANKS = new ModCreate("lode_planks", Block::new, ModBlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(2.2f)
            .sound(SoundType.STONE));
    public static final ModCreate LODE_SAPLING = new ModCreate("lode_sapling",
            p_368118_ -> new SaplingBlock(TreeGrower.OAK, p_368118_),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY));

    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength);
    }
}
