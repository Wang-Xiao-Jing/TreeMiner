package ctn.tree_miner.api;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xiao_jin.api.create.XiaoJinCreate;

import java.util.function.Function;
import java.util.function.Supplier;

import static ctn.tree_miner.create.TreeMinerBlocks.BLOCKS;
import static ctn.tree_miner.create.TreeMinerItems.ITEMS;

public class TreeMinerCreate extends XiaoJinCreate {

    public TreeMinerCreate(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties props, Item.Properties itemFunc) {
        super(name, func, props, itemFunc, BLOCKS, ITEMS);
    }

    public TreeMinerCreate(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties props) {
        super(name, func, props, BLOCKS, ITEMS);
    }
}
