package fuzs.stoneworks.fabric;

import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.stoneworks.common.Stoneworks;
import net.fabricmc.api.ModInitializer;

public class StoneworksFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
    }
}
