package fuzs.stoneworks.neoforge.client;

import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.client.StoneworksClient;
import fuzs.stoneworks.common.data.client.ModLanguageProvider;
import fuzs.stoneworks.common.data.client.ModModelProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Stoneworks.MOD_ID, dist = Dist.CLIENT)
public class StoneworksNeoForgeClient {

    public StoneworksNeoForgeClient() {
        ClientModConstructor.construct(Stoneworks.MOD_ID, StoneworksClient::new);
        DataProviderHelper.registerDataProviders(Stoneworks.MOD_ID, ModLanguageProvider::new, ModModelProvider::new);
    }
}
