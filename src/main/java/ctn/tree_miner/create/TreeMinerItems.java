package ctn.tree_miner.create;

import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.CreateItemAPI;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

/**
 * @author 尽
 * @apiNote 创建物品
 */
public class TreeMinerItems extends CreateItemAPI {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

//    public static final DeferredItem<Item> COAL_ORE_SAPLINGS = ITEMS.registerSimpleItem("coal_ore_saplings",
//            new Item.Properties().food(new FoodProperties.Builder()
//            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));
}
