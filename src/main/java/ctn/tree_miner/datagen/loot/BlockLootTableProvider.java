package ctn.tree_miner.datagen.loot;

import ctn.tree_miner.create.TreeMinerBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

import static ctn.tree_miner.create.TreeMinerBlocks.*;

/**
 * @author 尽
 * @apiNote 创建方块的战利品表
 */
public class BlockLootTableProvider extends BlockLootSubProvider {
    public BlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(LODE_SAPLING_COAL.block());
        dropSelf(LODE_SAPLING_IRON.block());
        dropSelf(LODE_SAPLING_COPPER.block());
        dropSelf(LODE_SAPLING_LAPIS.block());
        dropSelf(LODE_SAPLING_EMERALD.block());
        dropSelf(LODE_SAPLING_GOLD.block());
        dropSelf(LODE_SAPLING_REDSTONE.block());
        dropSelf(LODE_SAPLING_DIAMOND.block());
        dropSelf(NETHER_LODE_SAPLING_QUARTZ.block());
        dropSelf(NETHER_LODE_SAPLING_GLOWSTONE.block());
        dropSelf(NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block());
        dropSelf(NETHER_LODE_SAPLING_GOLD.block());
        addLeaves(LODE_LEAVES_COAL.block(), LODE_SAPLING_COAL.block(), 0.2F);
        addLeaves(LODE_LEAVES_IRON.block(), LODE_SAPLING_IRON.block(), 0.2F);
        addLeaves(LODE_LEAVES_COPPER.block(), LODE_SAPLING_COPPER.block(), 0.2F);
        addLeaves(LODE_LEAVES_LAPIS.block(), LODE_SAPLING_LAPIS.block(), 0.2F);
        addLeaves(LODE_LEAVES_EMERALD.block(), LODE_SAPLING_EMERALD.block(), 0.2F);
        addLeaves(LODE_LEAVES_GOLD.block(), LODE_SAPLING_GOLD.block(), 0.2F);
        addLeaves(LODE_LEAVES_REDSTONE.block(), LODE_SAPLING_REDSTONE.block(), 0.2F);
        addLeaves(LODE_LEAVES_DIAMOND.block(), LODE_SAPLING_DIAMOND.block(), 0.2F);
        addLeaves(NETHER_LODE_LEAVES_QUARTZ.block(), NETHER_LODE_SAPLING_QUARTZ.block(), 0.2F);
        addLeaves(NETHER_LODE_LEAVES_GLOWSTONE.block(), NETHER_LODE_SAPLING_GLOWSTONE.block(), 0.2F);
        addLeaves(NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block(), NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block(), 0.2F);
        addLeaves(NETHER_LODE_LEAVES_GOLD.block(), NETHER_LODE_SAPLING_GOLD.block(), 0.2F);
        dropSelf(LODE_LOG.block());
        dropSelf(NETHER_LODE_LOG.block());
        dropSelf(LODE_PLANKS.block());
        dropSelf(NETHER_LODE_PLANKS.block());
    }

    /**
     * 获取所有方块
     * @return
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected void addLeaves(Block leaves, Block sapling, float chance) {
        this.add(leaves, blocks -> createLeavesDrops(leaves, sapling, chance));
    }
}
