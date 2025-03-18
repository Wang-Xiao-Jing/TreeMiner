package ctn.tree_miner.create;

import com.mojang.serialization.Codec;
import ctn.tree_miner.api.TreeMinerCreate;
import ctn.tree_miner.server.TreeMinerTreeGrower;
import ctn.tree_miner.server.blocks.CrystalLodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeLeavesBlock;
import ctn.tree_miner.server.blocks.LodeSaplingBlock;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;
import xiao_jin.api.create.XiaoJinBlockBehaviour;
import xiao_jin.api.create.XiaoJinCreateBlock;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static ctn.tree_miner.TreeMinerMain.MOD_ID;
import static ctn.tree_miner.server.blocks.LodeSaplingBlock.SaplingProperties.saplingProperties;
import static net.minecraft.world.level.block.state.properties.WoodType.OAK;

/**
 * @author 尽
 * @apiNote 创建方块
 */
public class TreeMinerBlocks extends XiaoJinCreateBlock {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    public static final TreeMinerCreate LODE_LOG = new TreeMinerCreate("lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_PLANKS = new TreeMinerCreate("lode_planks", Block::new, createProperties(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate LODE_SLAB = new TreeMinerCreate("lode_slab", SlabBlock::new, createSlab(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate LODE_STAIR = new TreeMinerCreate("lode_stair", (it) -> new StairBlock(LODE_PLANKS.block().defaultBlockState(), it), createStair(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate LODE_FENCE = new TreeMinerCreate("lode_fence", FenceBlock::new, createFence(MapColor.STONE, SoundType.STONE , 3.0F));
    public static final TreeMinerCreate LODE_FENCE_GATE = new TreeMinerCreate("lode_fence_gate", (it) -> new FenceGateBlock(OAK, it), createGate(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));

    public static final TreeMinerCreate NETHER_LODE_LOG = new TreeMinerCreate("nether_lode_log", RotatedPillarBlock::new, lodeLogProperties(MapColor.NETHER, SoundType.NETHERRACK, 3.0F));
    public static final TreeMinerCreate NETHER_LODE_PLANKS = new TreeMinerCreate("nether_lode_planks", Block::new, createProperties(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));
    public static final TreeMinerCreate NETHER_LODE_SLAB = new TreeMinerCreate("nether_lode_slab", SlabBlock::new, createSlab(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));
    public static final TreeMinerCreate NETHER_LODE_STAIR = new TreeMinerCreate("nether_lode_stair", (it) -> new StairBlock(NETHER_LODE_PLANKS.block().defaultBlockState(), it), createStair(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));
    public static final TreeMinerCreate NETHER_LODE_FENCE = new TreeMinerCreate("nether_lode_fence", FenceBlock::new, createFence(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));
    public static final TreeMinerCreate NETHER_LODE_FENCE_GATE = new TreeMinerCreate("nether_lode_fence_gate", (it) -> new FenceGateBlock(OAK, it), createGate(MapColor.NETHER, SoundType.NETHERRACK , 2.0F));

    public static final TreeMinerCreate LODE_SAPLING_COAL = new TreeMinerCreate("lode_sapling_coal", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COAL, it, saplingProperties().placeable(BlockTags.COAL_ORES)), createSaplingProperties(MapColor.PLANT, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_IRON = new TreeMinerCreate("lode_sapling_iron", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_IRON, it, saplingProperties().placeable(BlockTags.IRON_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_COPPER = new TreeMinerCreate("lode_sapling_copper", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_COPPER, it, saplingProperties().placeable(BlockTags.COPPER_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_LAPIS = new TreeMinerCreate("lode_sapling_lapis", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_LAPIS, it, saplingProperties().placeable(BlockTags.LAPIS_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_EMERALD = new TreeMinerCreate("lode_sapling_emerald", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_EMERALD, it, saplingProperties().placeable(BlockTags.EMERALD_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_GOLD = new TreeMinerCreate("lode_sapling_gold", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_GOLD, it, saplingProperties().placeable(Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_REDSTONE = new TreeMinerCreate("lode_sapling_redstone", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_REDSTONE, it, saplingProperties().placeable(BlockTags.REDSTONE_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate LODE_SAPLING_DIAMOND = new TreeMinerCreate("lode_sapling_diamond", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.LODE_DIAMOND, it, saplingProperties().placeable(BlockTags.DIAMOND_ORES)), createSaplingProperties(MapColor.STONE, SoundType.STONE));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GLOWSTONE = new TreeMinerCreate("nether_lode_sapling_glowstone", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GLOWSTONE, it, saplingProperties().placeable(Blocks.GLOWSTONE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK).lightLevel(blockState -> 15));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_QUARTZ = new TreeMinerCreate("nether_lode_sapling_quartz", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_QUARTZ, it, saplingProperties().placeable(Blocks.NETHER_QUARTZ_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_GOLD = new TreeMinerCreate("nether_lode_sapling_gold", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_GOLD, it, saplingProperties().placeable(Blocks.NETHER_GOLD_ORE)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK));
    public static final TreeMinerCreate NETHER_LODE_SAPLING_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_sapling_ancient_debris", (it) -> new LodeSaplingBlock(TreeMinerTreeGrower.NETHER_LODE_ANCIENT_DEBRIS, it, saplingProperties().placeable(Blocks.ANCIENT_DEBRIS)), createSaplingProperties(MapColor.NETHER, SoundType.NETHERRACK), new Item.Properties().fireResistant());

    public static final TreeMinerCreate LODE_LEAVES_COAL = new TreeMinerCreate("lode_leaves_coal", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_COAL, LODE_SAPLING_COAL.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_IRON = new TreeMinerCreate("lode_leaves_iron", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_IRON, LODE_SAPLING_IRON.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_COPPER = new TreeMinerCreate("lode_leaves_copper", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_COPPER, LODE_SAPLING_COPPER.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_LAPIS = new TreeMinerCreate("lode_leaves_lapis", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_LAPIS, LODE_SAPLING_LAPIS.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_REDSTONE = new TreeMinerCreate("lode_leaves_redstone", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_REDSTONE, LODE_SAPLING_REDSTONE.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_GOLD = new TreeMinerCreate("lode_leaves_gold", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_GOLD, LODE_SAPLING_GOLD.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_EMERALD = new TreeMinerCreate("lode_leaves_emerald", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_EMERALD, LODE_SAPLING_EMERALD.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate LODE_LEAVES_DIAMOND = new TreeMinerCreate("lode_leaves_diamond", it -> new LodeLeavesBlock(it, TreeMinerItems.POD_DIAMOND, LODE_SAPLING_DIAMOND.blockItem), leavesProperties(MapColor.STONE, SoundType.STONE, 3.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GLOWSTONE = new TreeMinerCreate("nether_lode_leaves_glowstone", it -> new CrystalLodeLeavesBlock(it, TreeMinerItems.NETHER_POD_GLOWSTONE, NETHER_LODE_SAPLING_GLOWSTONE.blockItem), leavesProperties(MapColor.SAND, SoundType.GLASS, 2.0F).lightLevel(blockState -> 15));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_QUARTZ = new TreeMinerCreate("nether_lode_leaves_quartz", it -> new CrystalLodeLeavesBlock(it, TreeMinerItems.NETHER_POD_QUARTZ, NETHER_LODE_SAPLING_QUARTZ.blockItem), leavesProperties(MapColor.NETHER, SoundType.NETHER_ORE, 2.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_GOLD = new TreeMinerCreate("nether_lode_leaves_gold", it -> new CrystalLodeLeavesBlock(it, TreeMinerItems.NETHER_POD_GOLD, NETHER_LODE_SAPLING_GOLD.blockItem), leavesProperties(MapColor.NETHER, SoundType.NETHER_GOLD_ORE, 2.0F));
    public static final TreeMinerCreate NETHER_LODE_LEAVES_ANCIENT_DEBRIS = new TreeMinerCreate("nether_lode_leaves_ancient_debris", it -> new CrystalLodeLeavesBlock(it, TreeMinerItems.NETHER_POD_ANCIENT_DEBRIS, NETHER_LODE_SAPLING_ANCIENT_DEBRIS.blockItem), leavesProperties(MapColor.NETHER, SoundType.ANCIENT_DEBRIS, 10.0F, 1200.0F));

    public static BlockBehaviour.Properties lodeLogProperties(MapColor color, SoundType sound, float strength){
        return logProperties(color, color, sound, strength)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createProperties(MapColor color, SoundType sound, float strength){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(strength)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createSaplingProperties(MapColor color, SoundType sound){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .sound(sound)
                .pushReaction(PushReaction.DESTROY)
                .strength(0.3f)
                .noOcclusion().noCollission().randomTicks().instabreak();
    }

    public static final IntegerProperty STAGE_3 = IntegerProperty.create("stage", 0, 3);

    @Deprecated
    public static Block registerLegacyStair(String name, Block baseBlock) {
        return register(name, p_368077_ -> new StairBlock(baseBlock.defaultBlockState(), p_368077_), BlockBehaviour.Properties.ofLegacyCopy(baseBlock));
    }

    public static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        return register(vanillaBlockId(name), factory, properties);
    }

    public static ResourceKey<Block> vanillaBlockId(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.withDefaultNamespace(name));
    }

    private static Block register(ResourceKey<Block> resourceKey, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(resourceKey));
        return Registry.register(BuiltInRegistries.BLOCK, resourceKey, block);
    }

    public static BlockBehaviour.Properties createSlab(MapColor color, SoundType sound, float strength){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(strength)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createStair(MapColor color, SoundType sound, float strength){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(strength)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createFence(MapColor color, SoundType sound, float strength){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(strength)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    public static BlockBehaviour.Properties createGate(MapColor color, SoundType sound, float strength){
        return XiaoJinBlockBehaviour.Properties.of()
                .mapColor(color)
                .strength(strength)
                .sound(sound)
                .requiresCorrectToolForDrops();
    }

    // slab 台阶
    // Stair 楼梯
    // fence 栅栏
    // gate 栅栏门
}
