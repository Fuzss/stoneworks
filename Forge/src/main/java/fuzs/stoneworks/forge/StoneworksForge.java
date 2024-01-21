package fuzs.stoneworks.forge;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.stoneworks.Stoneworks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(Stoneworks.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StoneworksForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
    }
}
