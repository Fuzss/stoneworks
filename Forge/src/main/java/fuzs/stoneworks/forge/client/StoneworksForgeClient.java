package fuzs.stoneworks.forge.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.client.StoneworksClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod.EventBusSubscriber(modid = Stoneworks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class StoneworksForgeClient {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        ClientModConstructor.construct(Stoneworks.MOD_ID, StoneworksClient::new);
    }
}
