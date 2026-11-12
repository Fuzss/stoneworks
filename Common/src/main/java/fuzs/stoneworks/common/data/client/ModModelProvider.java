package fuzs.stoneworks.common.data.client;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.client.data.v3.models.AbstractModelProvider;
import fuzs.puzzleslib.common.api.client.data.v3.models.ModelLocationHelper;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModBlockVariants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ModModelProvider extends AbstractModelProvider {
    private static final ModelTemplate WALL_POST_COLUMN = createColumnWallTemplate(Stoneworks.id(
            "template_wall_post_column"), "_post");
    private static final ModelTemplate WALL_SIDE_COLUMN = createColumnWallTemplate(Stoneworks.id(
            "template_wall_side_column"), "_side");
    private static final ModelTemplate WALL_SIDE_TALL_COLUMN = createColumnWallTemplate(Stoneworks.id(
            "template_wall_side_tall_column"), "_side_tall");
    private static final ModelTemplate WALL_INVENTORY_COLUMN = createColumnWallTemplate(Stoneworks.id(
            "template_wall_inventory_column"), "_inventory");

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    private static ModelTemplate createColumnWallTemplate(Identifier id, String suffix) {
        return new ModelTemplate(Optional.of(ModelLocationHelper.getBlockModel(id)),
                Optional.of(suffix),
                TextureSlot.SIDE,
                TextureSlot.TOP,
                TextureSlot.BOTTOM);
    }

    @Override
    public void addBlockModels(BlockModelGenerators generators) {
        for (BlockSetFamily family : ModBlockFamilies.getBaseFamilies()) {
            Map<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> variantProviders = this.getVariantProviders(
                    family);
            for (BlockSetVariant variant : ModBlockVariants.VALUES) {
                Holder.Reference<Block> holder = family.getGeneratedBlockVariants().get(variant);
                if (holder != null) {
                    Block block = holder.value();
                    variantProviders.getOrDefault(variant, this::createCube).accept(generators, block);
                }

                BlockSetFamily childFamily = ModBlockFamilies.getChildFamily(family, variant);
                if (childFamily != null && !childFamily.getGeneratedBlockVariants().isEmpty()) {
                    if (ModBlockFamilies.COLUMN_SHAPE_FAMILIES.contains(family)
                            && variant == ModBlockVariants.REGULAR) {
                        this.createColumnShapes(generators, childFamily);
                    } else {
                        this.generateForBlocks(generators, childFamily);
                    }
                }
            }
        }
    }

    private Map<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> getVariantProviders(BlockSetFamily family) {
        ImmutableMap.Builder<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> builder = ImmutableMap.builder();
        builder.put(ModBlockVariants.PILLAR, this::createPillar);
        if (ModBlockFamilies.CHISELED_MOTIF_FAMILIES.contains(family)) {
            Block polishedBlock = Objects.requireNonNull(family.getBlock(ModBlockVariants.POLISHED),
                    "polished block is null").value();
            builder.put(ModBlockVariants.CHISELED, (BlockModelGenerators generators, Block block) -> {
                this.createChiseled(generators, block, polishedBlock);
            });
        }

        return builder.build();
    }

    public final void createPillar(BlockModelGenerators generators, Block block) {
        generators.createRotatedPillarWithHorizontalVariant(block,
                TexturedModel.COLUMN,
                TexturedModel.COLUMN_HORIZONTAL);
    }

    private void createChiseled(BlockModelGenerators generators, Block block, Block polishedBlock) {
        TextureMapping textureMapping = TextureMapping.column(ModelLocationHelper.getBlockTexture(block),
                ModelLocationHelper.getBlockTexture(polishedBlock));
        generators.new BlockFamilyProvider(textureMapping).fullBlock(block, ModelTemplates.CUBE_COLUMN);
    }

    private void createCube(BlockModelGenerators generators, Block block) {
        generators.new BlockFamilyProvider(TextureMapping.cube(block)).fullBlock(block, ModelTemplates.CUBE_ALL);
    }

    public final void createColumnShapes(BlockModelGenerators generators, BlockSetFamily childFamily) {
        TextureMapping textureMapping = TextureMapping.column(Blocks.BASALT);
        this.createColumnStairs(generators, childFamily.getBlock(BlockSetVariant.STAIRS).value(), textureMapping);
        this.createColumnSlab(generators,
                childFamily.getBlock(BlockSetVariant.SLAB).value(),
                textureMapping,
                Blocks.BASALT);
        this.createColumnWall(generators, childFamily.getBlock(BlockSetVariant.WALL).value(), textureMapping);
    }

    /**
     * @see net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider#stairs(Block)
     */
    public final void createColumnStairs(BlockModelGenerators generators, Block block, TextureMapping textureMapping) {
        Identifier straight = ModelTemplates.STAIRS_STRAIGHT.create(block, textureMapping, generators.modelOutput);
        Identifier inner = ModelTemplates.STAIRS_INNER.create(block, textureMapping, generators.modelOutput);
        Identifier outer = ModelTemplates.STAIRS_OUTER.create(block, textureMapping, generators.modelOutput);
        generators.blockStateOutput.accept(BlockModelGenerators.createStairs(block,
                BlockModelGenerators.plainVariant(inner),
                BlockModelGenerators.plainVariant(straight),
                BlockModelGenerators.plainVariant(outer)));
        generators.registerSimpleItemModel(block, straight);
    }

    /**
     * @see net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider#slab(Block)
     */
    public final void createColumnSlab(BlockModelGenerators generators, Block block, TextureMapping textureMapping, Block doubleBlock) {
        Identifier bottom = ModelTemplates.SLAB_BOTTOM.create(block, textureMapping, generators.modelOutput);
        Identifier top = ModelTemplates.SLAB_TOP.create(block, textureMapping, generators.modelOutput);
        generators.blockStateOutput.accept(BlockModelGenerators.createSlab(block,
                BlockModelGenerators.plainVariant(bottom),
                BlockModelGenerators.plainVariant(top),
                BlockModelGenerators.plainVariant(ModelLocationHelper.getBlockModel(doubleBlock))));
        generators.registerSimpleItemModel(block, bottom);
    }

    /**
     * @see net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider#wall(Block)
     */
    public final void createColumnWall(BlockModelGenerators generators, Block block, TextureMapping textureMapping) {
        Identifier post = WALL_POST_COLUMN.create(block, textureMapping, generators.modelOutput);
        Identifier side = WALL_SIDE_COLUMN.create(block, textureMapping, generators.modelOutput);
        Identifier sideTall = WALL_SIDE_TALL_COLUMN.create(block, textureMapping, generators.modelOutput);
        Identifier inventory = WALL_INVENTORY_COLUMN.create(block, textureMapping, generators.modelOutput);
        generators.blockStateOutput.accept(BlockModelGenerators.createWall(block,
                BlockModelGenerators.plainVariant(post),
                BlockModelGenerators.plainVariant(side),
                BlockModelGenerators.plainVariant(sideTall)));
        generators.registerSimpleItemModel(block, inventory);
    }
}
