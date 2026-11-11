package fuzs.stoneworks.common.data.tags;

import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v3.tags.AbstractTagsProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class ModBlockTagsProvider extends AbstractTagsProvider<Block> {

    public ModBlockTagsProvider(DataProviderContext context) {
        super(Registries.BLOCK, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        ModBlockFamilies.getAllBlockSetFamilies().flatMap((BlockSetFamily family) -> {
            return family.getGeneratedBlockVariants().values().stream();
        }).forEach((Holder.Reference<Block> holder) -> {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(holder);
        });
        ModBlockFamilies.getChildFamilies().forEach((BlockSetFamily family) -> {
            this.generateFor(family.getGeneratedBlockVariants(), VARIANT_STONE_BLOCK_TAGS);
        });
    }
}
