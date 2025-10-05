package fuzs.stoneworks.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.data.ModBlockLootProvider;
import fuzs.stoneworks.data.ModBlockTagsProvider;
import fuzs.stoneworks.data.ModRecipeProvider;
import net.neoforged.fml.common.Mod;

@Mod(Stoneworks.MOD_ID)
public class StoneworksNeoForge {

    public StoneworksNeoForge() {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
        DataProviderHelper.registerDataProviders(Stoneworks.MOD_ID,
                ModBlockLootProvider::new,
                ModBlockTagsProvider::new,
                ModRecipeProvider::new);
    }
}
