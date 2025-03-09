package ctn.tree_miner.create.template;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xiao_jin.api.create.template.ModCreateAPI;

import java.util.function.Function;

import static ctn.tree_miner.create.TreeMinerBlocks.BLOCKS;
import static ctn.tree_miner.create.TreeMinerItems.ITEMS;

/**
 * 便捷方块创建
 *
 * @author 尽
 * @apiNote 提供方块和对应的物品的同时创建
 */
public class ModCreate extends ModCreateAPI {

    /**
     * 构造函数，用于创建方块和对应的方块物品
     *
     * @param name           方块名称
     * @param func           方块构造函数
     * @param props          方块属性 {@linkplain BlockBehaviour.Properties}
     * @param itemFunc       方块物品构造函数
     */
    public ModCreate(String name,
                     Function<BlockBehaviour.Properties, ? extends Block> func,
                     BlockBehaviour.Properties props,
                     Function<Block, ? extends BlockItem> itemFunc) {
        super(name, func, props, itemFunc, BLOCKS, ITEMS);
    }

    /**
     * 构造函数，用于创建方块和对应的方块物品
     *
     * @param name           方块名称
     * @param func           方块构造函数
     * @param props          方块属性 {@linkplain BlockBehaviour.Properties}
     */
    public ModCreate(String name,
                     Function<BlockBehaviour.Properties, ? extends Block> func,
                     BlockBehaviour.Properties props) {
        super(name, func, props, BLOCKS, ITEMS);
    }
}
