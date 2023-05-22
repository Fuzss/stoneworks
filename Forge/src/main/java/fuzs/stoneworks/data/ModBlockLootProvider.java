package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v1.AbstractLootProvider;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(PackOutput packOutput, String modId) {
        super(packOutput, modId);
    }

    @Override
    public void generate() {
        StoneVariantsProvider.getStoneBlockVariants().<Block>mapMulti((variant, consumer) -> {
            consumer.accept(variant.block());
            consumer.accept(variant.stairs());
            consumer.accept(variant.wall());
        }).filter(Objects::nonNull).forEach(this::dropSelf);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::slab).filter(Objects::nonNull)
                .forEach(block -> this.add(block, this.createSlabItemTable(block)));
    }
}
