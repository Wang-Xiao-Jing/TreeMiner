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
        add(LODE_LEAVES.Block(), block -> createLeavesDrops(LODE_LEAVES.Block(),LODE_SAPLING.Block(), 0.2F));
        dropSelf(LODE_LOG.Block());
        dropSelf(LODE_PLANKS.Block());
        dropSelf(LODE_SAPLING.Block());
    }

    /**
     * 获取所有方块
     * @return
     */
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TreeMinerBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
