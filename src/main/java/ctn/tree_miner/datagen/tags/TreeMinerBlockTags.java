package ctn.tree_miner.datagen.tags;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import xiao_jin.api.datagen.tags.BlockTagsAPI;

import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

/**
 * @author 尽
 * @apiNote 方块标签数据生成器
 */
public class TreeMinerBlockTags extends BlockTagsAPI {
    public TreeMinerBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MOD_ID);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.LODE_LEAVES).add(TreeMinerBlocks.LODE_LEAVES.Block());
        tag(BlockTags.LODE_LOG).add(TreeMinerBlocks.LODE_LOG.Block());
    }

    public static class BlockTags{
        public static final TagKey<Block> LODE_LEAVES = tag("lode_leaves", MOD_ID);
        public static final TagKey<Block> LODE_LOG = tag("lode_log", MOD_ID);
    }
}
