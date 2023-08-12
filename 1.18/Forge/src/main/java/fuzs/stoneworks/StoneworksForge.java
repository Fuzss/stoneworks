package fuzs.stoneworks;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.stoneworks.data.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(Stoneworks.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StoneworksForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        evt.getGenerator().addProvider(new ModBlockLootProvider(evt, Stoneworks.MOD_ID));
        evt.getGenerator().addProvider(new ModBlockTagsProvider(evt, Stoneworks.MOD_ID));
        evt.getGenerator().addProvider(new ModLanguageProvider(evt, Stoneworks.MOD_ID));
        evt.getGenerator().addProvider(new ModModelProvider(evt, Stoneworks.MOD_ID));
        evt.getGenerator().addProvider(new ModRecipeProvider(evt, Stoneworks.MOD_ID));
    }
}
