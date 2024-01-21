package fuzs.stoneworks.neoforge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.data.ModBlockLootProvider;
import fuzs.stoneworks.data.ModBlockTagsProvider;
import fuzs.stoneworks.data.ModRecipeProvider;
import fuzs.stoneworks.data.client.ModLanguageProvider;
import fuzs.stoneworks.neoforge.data.client.ModModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;

@Mod(Stoneworks.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StoneworksNeoForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
        DataProviderHelper.registerDataProviders(Stoneworks.MOD_ID, ModBlockLootProvider::new, ModBlockTagsProvider::new, ModLanguageProvider::new, ModModelProvider::new, ModRecipeProvider::new);
    }
}
