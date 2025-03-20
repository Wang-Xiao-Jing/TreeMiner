package ctn.tree_miner.create;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.XiaoJinCreate;

import java.util.function.Function;

import static ctn.tree_miner.create.TreeMinerBlocks.BLOCKS;
import static ctn.tree_miner.create.TreeMinerItems.ITEMS;

public class TreeMinerBlockCreate extends XiaoJinCreate {

    public TreeMinerBlockCreate(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties props, Item.Properties itemFunc) {
        super(name, func, props, itemFunc, BLOCKS, ITEMS);
    }

    public TreeMinerBlockCreate(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties props) {
        super(name, func, props, BLOCKS, ITEMS);
    }

    public TreeMinerBlockCreate(String name, Function<BlockBehaviour.Properties, ? extends Block> func, BlockBehaviour.Properties props, DeferredRegister.Blocks deferredBlocks) {
        super(name, func, props, deferredBlocks, ITEMS);
    }
}
