package fuzs.stoneworks.data;

import fuzs.stoneworks.init.StoneVariantsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(DataGenerator gen, String modId, ExistingFileHelper exFileHelper) {
        super(gen, modId, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (StoneVariantsProvider.StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants()) {
            if (variant.blockVariant() == StoneVariantsProvider.BlockVariant.PILLAR) {
                this.axisBlock((RotatedPillarBlock) variant.block(), this.blockTexture(variant.name() + "_side"), this.blockTexture(variant.name() + "_top"));
                this.simpleBlockItem(variant.block(), variant.name());
            } else {
                ResourceLocation texture = this.blockTexture(variant.name());
                if (variant.blockVariant() == StoneVariantsProvider.BlockVariant.CHISELED && variant.stoneType().hasChiseledMotif()) {
                    this.simpleColumnBlock(variant.block(), texture, this.blockTexture(StoneVariantsProvider.BlockVariant.POLISHED.getName(variant.stoneType())));
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

    private String name(Block block) {
        return this.key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private ResourceLocation blockTexture(String block) {
        return this.modLoc(ModelProvider.BLOCK_FOLDER + "/" + block);
    }

    private void simpleBlockItem(Block block, String name) {
        this.simpleBlockItem(block, this.models().getExistingFile(this.modLoc(name)));
    }
}
