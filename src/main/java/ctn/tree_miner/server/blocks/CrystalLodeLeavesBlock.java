package ctn.tree_miner.server.blocks;

import ctn.tree_miner.datagen.tags.TreeMinerBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.checkerframework.checker.units.qual.C;

import java.util.function.Supplier;

/** 下界矿脉树叶 */
public class CrystalLodeLeavesBlock extends LodeLeavesBlock{
    public CrystalLodeLeavesBlock(Properties properties) {
        super(properties);
    }

    public CrystalLodeLeavesBlock(Properties properties, Supplier<Item> fruit, Supplier<BlockItem> sapling) {
        super(properties, fruit, sapling);
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
