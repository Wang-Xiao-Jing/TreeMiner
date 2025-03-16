package ctn.tree_miner.datagen.loot;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

import static ctn.tree_miner.create.TreeMinerBlocks.*;

/**
 * 创建方块的战利品表
 */
public class TreeMinerBlockLootTableProvider extends BlockLootSubProvider implements LootTableSubProvider {
    public TreeMinerBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(LODE_LOG.block());
        dropSelf(NETHER_LODE_LOG.block());
        dropSelf(LODE_PLANKS.block());
        dropSelf(NETHER_LODE_PLANKS.block());
        dropSelf(LODE_SAPLING_COAL.block());
        dropSelf(LODE_SAPLING_IRON.block());
        dropSelf(LODE_SAPLING_COPPER.block());
        dropSelf(LODE_SAPLING_LAPIS.block());
        dropSelf(LODE_SAPLING_EMERALD.block());
        dropSelf(LODE_SAPLING_GOLD.block());
        dropSelf(LODE_SAPLING_REDSTONE.block());
        dropSelf(LODE_SAPLING_DIAMOND.block());
        dropSelf(NETHER_LODE_SAPLING_QUARTZ.block());
        dropSelf(NETHER_LODE_SAPLING_GLOWSTONE.block());
        dropSelf(NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block());
        dropSelf(NETHER_LODE_SAPLING_GOLD.block());
    }

    public void createLodeFruit(Block block, Item item) {
        add(block, this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return getKnownBlocks(BLOCKS);
    }

    protected Iterable<Block> getKnownBlocks(DeferredRegister.Blocks deferredBlocks) {
        return deferredBlocks.getEntries().stream().map(Holder::value)::iterator;
    }

    protected void addLeavesRandom(Block leaves, Block sapling, float chance) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.add(leaves, blocks -> createSelfDropDispatchTable(leaves, this.hasSilkTouch(),
                ((LootPoolSingletonContainer.Builder<?>)this.applyExplosionCondition(leaves, LootItem.lootTableItem(sapling)))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), chance))));
    }

    protected void addLeavesRandom(Block leaves, Block sapling){
        addLeavesRandom(leaves, sapling, 0.1F);
    }
}

