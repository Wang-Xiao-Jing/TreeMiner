package ctn.tree_miner.server.blocks.sapling;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class OreSaplingBlock extends BushBlock implements BonemealableBlock {
//    public static final MapCodec<OreSaplingBlock> CODEC =
//            RecordCodecBuilder.mapCodec(builder ->
//                    builder.group(TreeMinerTreeGrower.CODEC.fieldOf("tree")
//                                    .forGetter(getter -> getter.treeGrower), propertiesCodec())
//                            .apply(builder, OreSaplingBlock::new)
//    );
    protected final TreeMinerTreeGrower treeGrower;
    private final Block[] placeableBlocks;
    private final TagKey<Block>[] placeableBlocksTag;
    private final Direction[] placeableDirection;

    @Override
    protected MapCodec<SaplingBlock> codec() {
        return SaplingBlock.CODEC;
    }

    public OreSaplingBlock(Properties properties, OreSaplingProperties oreSaplingProperties, TreeMinerTreeGrower treeGrower) {
        super(properties);
        this.placeableBlocks = oreSaplingProperties.getPlaceableBlocks();
        this.placeableDirection = oreSaplingProperties.getPlaceableDirection();
        this.placeableBlocksTag = oreSaplingProperties.getPlaceableBlocksTag();
        this.treeGrower = treeGrower;
    }

    @Override
    protected boolean mayPlaceOn( BlockState state,  BlockGetter level,  BlockPos pos) {
        boolean isPlaceableDirection = false;
        for (Direction direction : placeableDirection) {
            if (state.getBedDirection((LevelReader) level, pos) == direction) {
                isPlaceableDirection = true;
                break;
            }
        }
        if (!isPlaceableDirection) {
            return false;
        }
        for (Block block : placeableBlocks) {
            if (state.is(block)) {
                return true;
            }
        }
        for (TagKey<Block> tag : placeableBlocksTag) {
            if (state.is(tag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {

    }

    public static class OreSaplingProperties{
        private Block[] placeableBlocks = {Blocks.STONE};
        private Direction[] placeableDirection = {Direction.UP};
        private TagKey<Block>[] placeableBlocksTag;

        public void placeableBlocks(Block... placeableBlocks) {
            this.placeableBlocks = placeableBlocks;
        }

        public void placeableDirection(Direction... placeableDirection) {
            this.placeableDirection = placeableDirection;
        }

        public void placeableBlocksTag(TagKey<Block>... placeableBlocksTag) {
            this.placeableBlocksTag = placeableBlocksTag;
        }

        public TagKey<Block>[] getPlaceableBlocksTag() {
            return placeableBlocksTag;
        }

        public Direction[] getPlaceableDirection() {
            for (Direction direction : placeableDirection) {
                if (direction == null) {
                    throw new NullPointerException("[OreSaplingBlock:OreSaplingProperties]placeableDirection cannot be null");
                }
            }
            return placeableDirection;
        }

        public Block[] getPlaceableBlocks() {
            for (Block block : placeableBlocks) {
                if (block == null) {
                    throw new NullPointerException("[OreSaplingBlock:OreSaplingProperties]placeableBlocks cannot be null");
                }
            }
            return placeableBlocks;
        }
    }
}
