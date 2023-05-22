package fuzs.stoneworks;

import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class StoneworksFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(Stoneworks.MOD_ID, Stoneworks::new);
    }
}
