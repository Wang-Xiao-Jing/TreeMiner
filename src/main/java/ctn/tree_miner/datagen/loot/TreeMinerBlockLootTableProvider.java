package ctn.tree_miner.datagen.loot;

import ctn.tree_miner.create.TreeMinerItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import xiao_jin.api.datagen.loot.XiaoJinBlockLootTableProvider;

import java.util.List;

import static ctn.tree_miner.create.TreeMinerBlocks.*;

/**
 * 创建方块的战利品表
 */
public class TreeMinerBlockLootTableProvider extends XiaoJinBlockLootTableProvider {
    public TreeMinerBlockLootTableProvider(HolderLookup.Provider registries) {
        super(registries);
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
        addLeavesRandom(LODE_LEAVES_COAL.block(), LODE_SAPLING_COAL.block());
        addLeavesRandom(LODE_LEAVES_IRON.block(), LODE_SAPLING_IRON.block());
        addLeavesRandom(LODE_LEAVES_COPPER.block(), LODE_SAPLING_COPPER.block());
        addLeavesRandom(LODE_LEAVES_LAPIS.block(), LODE_SAPLING_LAPIS.block());
        addLeavesRandom(LODE_LEAVES_EMERALD.block(), LODE_SAPLING_EMERALD.block());
        addLeavesRandom(LODE_LEAVES_GOLD.block(), LODE_SAPLING_GOLD.block());
        addLeavesRandom(LODE_LEAVES_REDSTONE.block(), LODE_SAPLING_REDSTONE.block());
        addLeavesRandom(LODE_LEAVES_DIAMOND.block(), LODE_SAPLING_DIAMOND.block());
        addLeavesRandom(NETHER_LODE_LEAVES_QUARTZ.block(), NETHER_LODE_SAPLING_QUARTZ.block());
        addLeavesRandom(NETHER_LODE_LEAVES_GLOWSTONE.block(), NETHER_LODE_SAPLING_GLOWSTONE.block());
        addLeavesRandom(NETHER_LODE_LEAVES_ANCIENT_DEBRIS.block(), NETHER_LODE_SAPLING_ANCIENT_DEBRIS.block());
        addLeavesRandom(NETHER_LODE_LEAVES_GOLD.block(), NETHER_LODE_SAPLING_GOLD.block());

        createLodeFruit(LODE_FRUIT_COAL.get(), TreeMinerItems.POD_COAL.get());
        createLodeFruit(LODE_FRUIT_IRON.get(), TreeMinerItems.POD_IRON.get());
        createLodeFruit(LODE_FRUIT_COPPER.get(), TreeMinerItems.POD_COPPER.get());
        createLodeFruit(LODE_FRUIT_LAPIS.get(), TreeMinerItems.POD_LAPIS.get());
        createLodeFruit(LODE_FRUIT_EMERALD.get(), TreeMinerItems.POD_EMERALD.get());
        createLodeFruit(LODE_FRUIT_GOLD.get(), TreeMinerItems.POD_GOLD.get());
        createLodeFruit(LODE_FRUIT_REDSTONE.get(), TreeMinerItems.POD_REDSTONE.get());
        createLodeFruit(LODE_FRUIT_DIAMOND.get(), TreeMinerItems.POD_DIAMOND.get());
        createLodeFruit(NETHER_LODE_FRUIT_QUARTZ.get(), TreeMinerItems.NETHER_POD_QUARTZ.get());
        createLodeFruit(NETHER_LODE_FRUIT_GLOWSTONE.get(), TreeMinerItems.NETHER_POD_GLOWSTONE.get());
        createLodeFruit(NETHER_LODE_FRUIT_ANCIENT_DEBRIS.get(), TreeMinerItems.NETHER_POD_ANCIENT_DEBRIS.get());
        createLodeFruit(NETHER_LODE_FRUIT_GOLD.get(), TreeMinerItems.NETHER_POD_GOLD.get());
    }

    public void createLodeFruit(Block block, Item item) {
        add(block, this.createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return super.getKnownBlocks(BLOCKS);
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

