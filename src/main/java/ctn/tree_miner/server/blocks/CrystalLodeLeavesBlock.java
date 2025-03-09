package ctn.tree_miner.server.blocks;

import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CrystalLodeLeavesBlock extends LodeLeavesBlock{
    /**
     * 构造函数，初始化矿脉树叶块的默认状态
     *
     * @param properties 块的属性
     */
    public CrystalLodeLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TagKey<Block> getLodeLeaves() {
        return TreeMinerBlockTags.BlockTags.NETHER_LODE_LEAVES;
    }

    @Override
    public TagKey<Block> getLodeLog() {
        return TreeMinerBlockTags.BlockTags.NETHER_LODE_LOG;
    }
}
