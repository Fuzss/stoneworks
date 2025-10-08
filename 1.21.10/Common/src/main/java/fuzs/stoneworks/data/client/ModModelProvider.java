package fuzs.stoneworks.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractModelProvider;
import fuzs.puzzleslib.api.client.data.v2.models.ModelLocationHelper;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.world.block.variant.BlockVariant;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addBlockModels(BlockModelGenerators blockModelGenerators) {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            if (variant.blockVariant() == BlockVariant.PILLAR) {
                blockModelGenerators.createRotatedPillarWithHorizontalVariant(variant.block(),
                        TexturedModel.COLUMN,
                        TexturedModel.COLUMN_HORIZONTAL);
            } else {
                ModelTemplate modelTemplate;
                TextureMapping textureMapping;
                if (variant.blockVariant() == BlockVariant.CHISELED && variant.stoneType().hasChiseledMotif()) {
                    StoneBlockVariant polishedVariant = StoneVariantsProvider.getStoneVariant(variant.stoneType(),
                            BlockVariant.POLISHED);
                    modelTemplate = ModelTemplates.CUBE_COLUMN;
                    textureMapping = TextureMapping.column(ModelLocationHelper.getBlockTexture(variant.block()),
                            ModelLocationHelper.getBlockTexture(polishedVariant.block()));
                } else {
                    modelTemplate = ModelTemplates.CUBE_ALL;
                    textureMapping = TextureMapping.cube(variant.block());
                }
                BlockModelGenerators.BlockFamilyProvider blockFamilyProvider = blockModelGenerators.new BlockFamilyProvider(
                        textureMapping).fullBlock(variant.block(), modelTemplate);
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    blockFamilyProvider.stairs(variant.stairs()).slab(variant.slab()).wall(variant.wall());
                }
            }
        }
    }

    @Override
    public void addItemModels(ItemModelGenerators itemModelGenerators) {
        super.addItemModels(itemModelGenerators);
    }
}
