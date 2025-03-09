package ctn.tree_miner.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import xiao_jin.api.datagen.tags.BlockTagsAPI;

import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerBlocks.*;

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
        tag(BlockTags.LODE_LEAVES)
                .add(LODE_LEAVES_COAL.block())
                .add(LODE_LEAVES_IRON.block())
                .add(LODE_LEAVES_COPPER.block())
                .add(LODE_LEAVES_LAPIS.block())
                .add(LODE_LEAVES_EMERALD.block())
                .add(LODE_LEAVES_GOLD.block())
                .add(LODE_LEAVES_REDSTONE.block())
                .add(LODE_LEAVES_DIAMOND.block());
        tag(BlockTags.LODE_LOG)
                .add(LODE_LOG.block());
        tag(BlockTags.LODE_SAPLING)
                .add(LODE_SAPLING_COAL.block())
                .add(LODE_SAPLING_IRON.block())
                .add(LODE_SAPLING_COPPER.block())
                .add(LODE_SAPLING_LAPIS.block())
                .add(LODE_SAPLING_EMERALD.block())
                .add(LODE_SAPLING_GOLD.block())
                .add(LODE_SAPLING_REDSTONE.block())
                .add(LODE_SAPLING_DIAMOND.block());
        tag(BlockTags.NETHER_LODE_LEAVES)
                .add(NETHER_LODE_LEAVES_QUARTZ.block())
                .add(NETHER_LODE_LEAVES_GLOWSTONE.block())
                .add(NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block())
                .add(NETHER_LODE_LEAVES_GOLD.block());
        tag(BlockTags.NETHER_LODE_LOG)
                .add(NETHER_LODE_LOG.block());
        tag(BlockTags.NETHER_LODE_SAPLING)
                .add(NETHER_LODE_SAPLING_QUARTZ.block())
                .add(NETHER_LODE_SAPLING_GLOWSTONE.block())
                .add(NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block())
                .add(NETHER_LODE_SAPLING_GOLD.block());
    }

    public static class BlockTags{
        public static final TagKey<Block> LODE_LEAVES = tag("lode_leaves", MOD_ID);
        public static final TagKey<Block> LODE_LOG = tag("lode_log", MOD_ID);
        public static final TagKey<Block> LODE_SAPLING = tag("lode_sapling", MOD_ID);
        public static final TagKey<Block> NETHER_LODE_LEAVES = tag("nether_lode_leaves", MOD_ID);
        public static final TagKey<Block> NETHER_LODE_LOG = tag("nether_lode_log", MOD_ID);
        public static final TagKey<Block> NETHER_LODE_SAPLING = tag("nether_lode_sapling", MOD_ID);
    }
}
