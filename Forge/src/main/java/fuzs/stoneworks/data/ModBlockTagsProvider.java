package fuzs.stoneworks.data;

import fuzs.stoneworks.init.StoneVariantsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator dataGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        StoneVariantsProvider.getStoneBlockVariants().stream().flatMap(StoneVariantsProvider.StoneBlockVariant::allBlocks).forEach(this.tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
        StoneVariantsProvider.getStoneBlockVariants().stream().map(StoneVariantsProvider.StoneBlockVariant::stairs).filter(Objects::nonNull).forEach(this.tag(BlockTags.STAIRS)::add);
        StoneVariantsProvider.getStoneBlockVariants().stream().map(StoneVariantsProvider.StoneBlockVariant::slab).filter(Objects::nonNull).forEach(this.tag(BlockTags.SLABS)::add);
        StoneVariantsProvider.getStoneBlockVariants().stream().map(StoneVariantsProvider.StoneBlockVariant::wall).filter(Objects::nonNull).forEach(this.tag(BlockTags.WALLS)::add);
    }
}