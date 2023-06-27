package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v1.AbstractTagProvider;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends AbstractTagProvider.Blocks {

    public ModBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, ExistingFileHelper fileHelper) {
        super(packOutput, lookupProvider, modId, fileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        StoneVariantsProvider.getStoneBlockVariants().flatMap(StoneBlockVariant::allBlocks).forEach(this.tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::stairs).filter(Objects::nonNull).forEach(this.tag(BlockTags.STAIRS)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::slab).filter(Objects::nonNull).forEach(this.tag(BlockTags.SLABS)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::wall).filter(Objects::nonNull).forEach(this.tag(BlockTags.WALLS)::add);
    }
}