package fuzs.stoneworks.neoforge.data.client;

import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.puzzleslib.neoforge.api.data.v2.client.AbstractModelProvider;
import fuzs.puzzleslib.neoforge.api.data.v2.core.NeoForgeDataProviderContext;
import fuzs.stoneworks.world.block.variant.BlockVariant;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ModelProvider;

public class ModModelProvider extends AbstractModelProvider {

    public ModModelProvider(NeoForgeDataProviderContext context) {
        super(context);
    }

    @Override
    protected void registerStatesAndModels() {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            if (variant.blockVariant() == BlockVariant.PILLAR) {
                this.axisBlock((RotatedPillarBlock) variant.block(), this.blockTexture(variant.name() + "_side"), this.blockTexture(variant.name() + "_top"));
                this.simpleBlockItem(variant.block(), variant.name());
            } else {
                ResourceLocation texture = this.blockTexture(variant.name());
                if (variant.blockVariant() == BlockVariant.CHISELED && variant.stoneType().hasChiseledMotif()) {
                    StoneBlockVariant polishedVariant = StoneVariantsProvider.getStoneVariant(variant.stoneType(), BlockVariant.POLISHED);
                    this.simpleColumnBlock(variant.block(), texture, this.blockTexture(polishedVariant.id(polishedVariant.name())));
                } else {
                    this.simpleBlock(variant.block());
                }
                this.simpleBlockItem(variant.block(), variant.name());
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    this.stairsBlock((StairBlock) variant.stairs(), texture);
                    this.itemModels().stairs(variant.stairsName(), texture, texture, texture);
                    this.slabBlock((SlabBlock) variant.slab(), texture, texture);
                    this.itemModels().slab(variant.slabName(), texture, texture, texture);
                    this.wallBlock((WallBlock) variant.wall(), texture);
                    this.itemModels().wallInventory(variant.wallName(), texture);
                }
            }
        }
    }

    public void simpleColumnBlock(Block block, ResourceLocation side, ResourceLocation end) {
        this.simpleBlock(block, this.models().cubeColumn(this.name(block), side, end));
    }

    private ResourceLocation blockTexture(String block) {
        return this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + block);
    }

    private ResourceLocation blockTexture(ResourceLocation id) {
        return ResourceLocationHelper.fromNamespaceAndPath(id.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + id.getPath());
    }

    private void simpleBlockItem(Block block, String name) {
        this.simpleBlockItem(block, this.models().getExistingFile(this.modLoc(name)));
    }
}
