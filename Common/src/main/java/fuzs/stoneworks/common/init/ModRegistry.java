package fuzs.stoneworks.common.init;

import fuzs.puzzleslib.common.api.init.v3.registry.RegistryManager;
import fuzs.stoneworks.common.Stoneworks;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Stoneworks.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab("main",
            () -> new ItemStack(Items.STONE),
            (CreativeModeTab.DisplayItemsGenerator generator) -> {
                return (CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) -> {
                    ModBlockFamilies.getSortedVariantItems().forEach(output::accept);
                };
            },
            true);

    private ModRegistry() {
        // NO-OP
    }

    public static void bootstrap() {
        ModBlockFamilies.bootstrap();
    }
}
