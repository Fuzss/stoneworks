package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Consumer;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        StoneVariantsProvider.getStoneBlockVariants().<Block>mapMulti((StoneBlockVariant variant, Consumer<Block> consumer) -> {
            consumer.accept(variant.block());
            consumer.accept(variant.stairs());
            consumer.accept(variant.wall());
        }).filter(Objects::nonNull).forEach(this::dropSelf);
        StoneVariantsProvider.getStoneBlockVariants().map(StoneBlockVariant::slab).filter(Objects::nonNull)
                .forEach(block -> this.add(block, this.createSlabItemTable(block)));
    }
}
