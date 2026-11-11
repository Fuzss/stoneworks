package fuzs.stoneworks.common.data.client;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.client.data.v3.models.AbstractModelProvider;
import fuzs.puzzleslib.common.api.client.data.v3.models.ModelLocationHelper;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModBlockVariants;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators generators) {
        for (BlockSetFamily family : ModBlockFamilies.ROOT_FAMILIES) {
            Map<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> variantProviders = this.getVariantProviders(
                    family);
            for (BlockSetVariant variant : ModBlockVariants.VALUES) {
                Holder.Reference<Block> holder = family.getGeneratedBlockVariants().get(variant);
                if (holder == null) {
                    continue;
                }

                Block block = holder.value();
                variantProviders.getOrDefault(variant, this::createCube).accept(generators, block);

                BlockSetFamily childFamily = ModBlockFamilies.getChildFamily(family, variant);
                if (childFamily != null) {
                    this.generateForBlocks(generators, childFamily);
                }
            }
        }
    }

    private Map<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> getVariantProviders(BlockSetFamily family) {
        ImmutableMap.Builder<BlockSetVariant, BiConsumer<BlockModelGenerators, Block>> builder = ImmutableMap.builder();
        builder.put(ModBlockVariants.PILLAR, this::createPillar);
        if (ModBlockFamilies.hasChiseledMotif(family)) {
            Block polishedBlock = Objects.requireNonNull(family.getBlock(ModBlockVariants.POLISHED),
                    "polished block is null").value();
            builder.put(ModBlockVariants.CHISELED, (BlockModelGenerators generators, Block block) -> {
                this.createChiseled(generators, block, polishedBlock);
            });
        }

        return builder.build();
    }

    private void createPillar(BlockModelGenerators generators, Block block) {
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
}
