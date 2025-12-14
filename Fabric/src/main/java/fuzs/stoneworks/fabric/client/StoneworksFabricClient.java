package fuzs.stoneworks.fabric.client;

import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.client.StoneworksClient;
import net.fabricmc.api.ClientModInitializer;

public class StoneworksFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(Stoneworks.MOD_ID, StoneworksClient::new);
    }
}
