package ctn.tree_miner.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xiao_jin.api.datagen.tags.XiaoJinItemTags;

import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.create.TreeMinerItems.*;

/**
 * @author 尽
 * @apiNote 物品标签数据生成器
 */
public class TreeMinerItemTags extends XiaoJinItemTags<Item> {
    public TreeMinerItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, MOD_ID);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.COOKED_NETHER_POD)
                .add(COOKED_POD_COAL.get())
                .add(COOKED_POD_COPPER.get())
                .add(COOKED_POD_DIAMOND.get())
                .add(COOKED_POD_EMERALD.get())
                .add(COOKED_POD_GOLD.get())
                .add(COOKED_POD_IRON.get())
                .add(COOKED_POD_LAPIS.get())
                .add(COOKED_POD_REDSTONE.get())
                .add(COOKED_NETHER_POD_NETHERITE.get())
                .add(COOKED_NETHER_POD_GLOWSTONE.get())
                .add(COOKED_NETHER_POD_GOLD.get())
                .add(COOKED_NETHER_POD_QUARTZ.get());
        tag(ItemTags.NETHER_POD)
                .add(POD_COAL.get())
                .add(POD_COPPER.get())
                .add(POD_DIAMOND.get())
                .add(POD_EMERALD.get())
                .add(POD_GOLD.get())
                .add(POD_IRON.get())
                .add(POD_LAPIS.get())
                .add(POD_REDSTONE.get());
        tag(ItemTags.NETHER_NETHER_POD)
                .add(NETHER_POD_NETHERITE.get())
                .add(NETHER_POD_GLOWSTONE.get())
                .add(NETHER_POD_GOLD.get())
                .add(NETHER_POD_QUARTZ.get());
        tag(net.minecraft.tags.ItemTags.WOODEN_TOOL_MATERIALS)
                .addTag(ItemTags.LODE_PLANKS);
        copy(TreeMinerBlockTags.BlockTags.LODE_PLANKS, ItemTags.LODE_PLANKS);
    }

    public static class ItemTags {
        public static final TagKey<Item> COOKED_NETHER_POD = tag("cooked_nether_pod", MOD_ID);
        public static final TagKey<Item> NETHER_POD = tag("nether_pod", MOD_ID);
        public static final TagKey<Item> NETHER_NETHER_POD = tag("nether_nether_pod", MOD_ID);
        public static final TagKey<Item> LODE_PLANKS = tag("lode_planks", MOD_ID);
    }
}
