package ctn.tree_miner.datagen.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xiao_jin.api.datagen.tags.ItemTagsAPI;

import java.util.concurrent.CompletableFuture;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;

/**
 * @author 尽
 * @apiNote 物品标签数据生成器
 */
public class TreeMinerItemTags extends ItemTagsAPI<Item> {
    public TreeMinerItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags, MOD_ID);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {

    }
}
