package fuzs.stoneworks.common.data.loot;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModBlockVariants;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.BiConsumer;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {
    public static final Map<BlockSetVariant, BiConsumer<AbstractBlockLootSubProvider, Block>> VARIANT_PROVIDERS = ImmutableMap.<BlockSetVariant, BiConsumer<AbstractBlockLootSubProvider, Block>>builder()
            .putAll(AbstractBlockLootSubProvider.VARIANT_PROVIDERS)
            .put(ModBlockVariants.REGULAR, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.MOSSY_COBBLED, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.MOSSY_BRICKS, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.CRACKED_BRICKS, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.CHISELED, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.CRACKED_TILES, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.SHINGLES, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.PAVERS, BlockLootSubProvider::dropSelf)
            .put(ModBlockVariants.PLATES, BlockLootSubProvider::dropSelf)
            .buildKeepingLast();

    public ModBlockLootProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    public void generate() {
        ModBlockFamilies.getAllFamilies().forEach((BlockSetFamily family) -> {
            this.generateFor(family, VARIANT_PROVIDERS);
        });
    }
}
