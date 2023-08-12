package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v1.AbstractTagProvider;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.Objects;

public class ModBlockTagsProvider extends AbstractTagProvider.Blocks {

    public ModBlockTagsProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void addTags() {
        StoneVariantsProvider.getStoneBlockVariants().flatMap(StoneBlockVariant::allBlocks).forEach(this.tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::stairs).filter(Objects::nonNull).forEach(this.tag(BlockTags.STAIRS)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::slab).filter(Objects::nonNull).forEach(this.tag(BlockTags.SLABS)::add);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::wall).filter(Objects::nonNull).forEach(this.tag(BlockTags.WALLS)::add);
    }
}