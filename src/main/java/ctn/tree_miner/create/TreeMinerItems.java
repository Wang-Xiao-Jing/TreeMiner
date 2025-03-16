package ctn.tree_miner.create;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
/**
 * @author 尽
 * @apiNote 创建物品
 */
public class TreeMinerItems{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredItem<Item> POD_COAL = ITEMS.registerItem("pod_coal", Item::new);
    public static final DeferredItem<Item> POD_IRON = ITEMS.registerItem("pod_iron", Item::new);
    public static final DeferredItem<Item> POD_COPPER = ITEMS.registerItem("pod_copper", Item::new);
    public static final DeferredItem<Item> POD_LAPIS = ITEMS.registerItem("pod_lapis", Item::new);
    public static final DeferredItem<Item> POD_EMERALD = ITEMS.registerItem("pod_emerald", Item::new);
    public static final DeferredItem<Item> POD_GOLD = ITEMS.registerItem("pod_gold", Item::new);
    public static final DeferredItem<Item> POD_REDSTONE = ITEMS.registerItem("pod_redstone", Item::new);
    public static final DeferredItem<Item> POD_DIAMOND = ITEMS.registerItem("pod_diamond", Item::new);
    public static final DeferredItem<Item> NETHER_POD_GLOWSTONE = ITEMS.registerItem("nether_pod_glowstone", Item::new);
    public static final DeferredItem<Item> NETHER_POD_QUARTZ = ITEMS.registerItem("nether_pod_quartz", Item::new);
    public static final DeferredItem<Item> NETHER_POD_GOLD = ITEMS.registerItem("nether_pod_gold", Item::new);
    public static final DeferredItem<Item> NETHER_POD_ANCIENT_DEBRIS = ITEMS.registerItem("nether_pod_ancient_debris", Item::new, new Item.Properties().fireResistant());

}
