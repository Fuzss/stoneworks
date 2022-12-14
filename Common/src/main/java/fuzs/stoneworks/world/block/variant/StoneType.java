package fuzs.stoneworks.world.block.variant;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Locale;

public enum StoneType {
    STONE(Blocks.STONE), ANDESITE(Blocks.ANDESITE), GRANITE(Blocks.GRANITE), DIORITE(Blocks.DIORITE), DEEPSLATE(Blocks.DEEPSLATE), CALCITE(Blocks.CALCITE), TUFF(Blocks.TUFF), BASALT(Blocks.BASALT), BLACKSTONE(Blocks.BLACKSTONE), NETHERRACK(Blocks.NETHERRACK, false) {
        @Override
        public Block getBaseBlock(BlockVariant blockVariant) {
            if (blockVariant == BlockVariant.CHISELED || blockVariant == BlockVariant.TILES || blockVariant == BlockVariant.CRACKED_TILES || blockVariant == BlockVariant.PILLAR || blockVariant == BlockVariant.SHINGLES || blockVariant == BlockVariant.PAVERS || blockVariant == BlockVariant.PLATES) {
                return Blocks.NETHER_BRICKS;
            }
            return super.getBaseBlock(blockVariant);
        }
    }, END_STONE(Blocks.END_STONE, false), PURPUR(Blocks.PURPUR_BLOCK, false), PRISMARINE(Blocks.PRISMARINE, false), DARK_PRISMARINE(Blocks.DARK_PRISMARINE, false), SANDSTONE(Blocks.SANDSTONE, false), RED_SANDSTONE(Blocks.RED_SANDSTONE, false), QUARTZ(Blocks.QUARTZ_BLOCK, false);

    private final Block baseBlock;
    private final boolean isCobbleHardened;

    StoneType(Block baseBlock) {
        this(baseBlock, true);
    }

    StoneType(Block baseBlock, boolean isCobbleHardened) {
        this.baseBlock = baseBlock;
        this.isCobbleHardened = isCobbleHardened;
    }

    public Block getBaseBlock(BlockVariant blockVariant) {
        return this.baseBlock;
    }

    public BlockState getDefaultBlockState(BlockVariant blockVariant) {
        return this.getBaseBlock(blockVariant).defaultBlockState();
    }

    protected BlockBehaviour.Properties getBlockProperties(BlockVariant blockVariant) {
        Block baseBlock = this.getBaseBlock(blockVariant);
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(baseBlock);
        if (this.isCobbleHardened && blockVariant != BlockVariant.REGULAR) {
            properties.strength(baseBlock.defaultDestroyTime() + 0.5F, baseBlock.getExplosionResistance());
        }
        return properties;
    }

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public boolean hasChiseledMotif() {
        // no basalt it looks bad, also vanilla variants are ignored anyway
        return this == DIORITE || this == DEEPSLATE || this == CALCITE || this == TUFF || this == BLACKSTONE || this == NETHERRACK || this == PRISMARINE || this == DARK_PRISMARINE || this == SANDSTONE || this == RED_SANDSTONE;
    }
}
