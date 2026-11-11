package fuzs.stoneworks.common.data.loot;

import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import net.minecraft.core.Holder;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Block;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    public void generate() {
        ModBlockFamilies.getBaseFamilies().stream().flatMap((BlockSetFamily family) -> {
            return family.getGeneratedBlockVariants().values().stream();
        }).forEach((Holder.Reference<Block> holder) -> {
            this.dropSelf(holder.value());
        });
        ModBlockFamilies.getChildFamilies().forEach(this::generateFor);
    }
}
