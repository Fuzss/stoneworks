package fuzs.stoneworks;

import fuzs.puzzleslib.core.CommonFactories;
import net.fabricmc.api.ModInitializer;

public class StoneworksFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonFactories.INSTANCE.modConstructor(Stoneworks.MOD_ID).accept(new Stoneworks());
    }
}
