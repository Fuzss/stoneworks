package fuzs.stoneworks.neoforge;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.data.loot.ModBlockLootProvider;
import fuzs.stoneworks.common.data.tags.ModBlockTagsProvider;
import fuzs.stoneworks.common.data.ModRecipeProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.fml.common.Mod;

@Mod(Stoneworks.MOD_ID)
public class StoneworksNeoForge {

    public StoneworksNeoForge() {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
        DataProviderBuilder.of(Stoneworks.MOD_ID)
                .addLootProvider(ModBlockLootProvider::new, LootContextParamSets.BLOCK)
                .addProvider(ModBlockTagsProvider::new)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}
