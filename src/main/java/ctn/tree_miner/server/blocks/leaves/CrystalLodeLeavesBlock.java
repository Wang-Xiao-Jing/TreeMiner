package ctn.tree_miner.server.blocks.leaves;

import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/** 下界矿脉树叶 */
public class CrystalLodeLeavesBlock extends LodeLeavesBlock{
    public CrystalLodeLeavesBlock(Properties properties) {
        super(properties);
    }

    public CrystalLodeLeavesBlock(Properties properties, Block generateBlock) {
        super(properties, generateBlock);
    }

    @Override
    public TagKey<Block> getLodeLeaves() {
        return TreeMinerBlockTags.Tags.NETHER_LODE_LEAVES;
    }

    @Override
    public TagKey<Block> getLodeLog() {
        return TreeMinerBlockTags.Tags.NETHER_LODE_LOG;
    }
}
