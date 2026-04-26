package fuzs.stoneworks.fabric.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.client.StoneworksClient;
import net.fabricmc.api.ClientModInitializer;

public class StoneworksFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(Stoneworks.MOD_ID, StoneworksClient::new);
    }
}
