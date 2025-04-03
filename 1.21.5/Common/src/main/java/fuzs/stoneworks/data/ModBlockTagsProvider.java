package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.api.data.v2.tags.AbstractTagProvider;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public class ModBlockTagsProvider extends AbstractTagProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        StoneVariantsProvider.getStoneBlockVariants()
                .flatMap(StoneBlockVariant::allBlocks)
                .forEach(this.tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
        StoneVariantsProvider.getStoneBlockVariants()
                .map(StoneBlockVariant::stairs)
                .filter(Objects::nonNull)
                .forEach(this.tag(BlockTags.STAIRS)::add);
        StoneVariantsProvider.getStoneBlockVariants()
                .map(StoneBlockVariant::slab)
                .filter(Objects::nonNull)
                .forEach(this.tag(BlockTags.SLABS)::add);
        StoneVariantsProvider.getStoneBlockVariants()
                .map(StoneBlockVariant::wall)
                .filter(Objects::nonNull)
                .forEach(this.tag(BlockTags.WALLS)::add);
    }
}