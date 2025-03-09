package ctn.tree_miner.create;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.CreateCreationModeTabAPI;

import java.util.function.Supplier;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static net.minecraft.world.item.Items.OAK_SAPLING;

/**
 * @author 尽
 * @apiNote 创建一个创造模式物品栏
 */
public class CreativeItemsModeTab extends CreateCreationModeTabAPI {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = getCreativeModeTabs(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TREE_MINER_TAB = register(
            "tree_miner_tab",
            CreativeModeTabs.COMBAT,
            OAK_SAPLING::getDefaultInstance/*COAL_ORE_SAPLINGS.get().getDefaultInstance()*/,
            (parameters, output) ->{
                output.accept(TreeMinerBlocks.LODE_LOG.Item());
                output.accept(TreeMinerBlocks.LODE_LEAVES.Item());
//                    output.accept(sapling);
//                    output.accept(EXAMPLE_BLOCK.blockItem);
            });

    /**
     * 添加到创造模式物品栏
     */
    public static void registerCapabilities(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK.blockItem);
    }

    /**
     * 创建一个创造模式物品栏
     *
     * @param name                  命名空间
     * @param withTabsBefore        父物品栏
     * @param icon                  图标
     * @param displayItemsGenerator 显示物品
     * @return 延迟注册器
     */
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
            String name,
            ResourceKey<CreativeModeTab> withTabsBefore,
            Supplier<ItemStack> icon,
            CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
        return register(name, withTabsBefore, icon, displayItemsGenerator, CREATIVE_MODE_TABS);
    }

    /**
     * 创建一个创造模式物品栏
     *
     * @param name           命名空间
     * @param withTabsBefore 父物品栏
     * @param icon           图标
     * @return 延迟注册器
     */
    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(
            String name,
            ResourceKey<CreativeModeTab> withTabsBefore,
            Supplier<ItemStack> icon) {
        return register(name, withTabsBefore, icon, (parameters, output) -> {}, CREATIVE_MODE_TABS);
    }
}
