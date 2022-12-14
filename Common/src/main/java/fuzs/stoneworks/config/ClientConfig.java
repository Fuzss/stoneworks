package fuzs.stoneworks.config;

import fuzs.puzzleslib.config.ConfigCore;
import fuzs.puzzleslib.config.annotation.Config;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;

public class ClientConfig implements ConfigCore {
    @Config(description = "Add vanilla stone block variants to the creative tab provided by Stoneworks.")
    public boolean vanillaVariantsInCreativeTab = true;

    @Override
    public void afterConfigReload() {
        StoneVariantsProvider.invalidateItems();
    }
}
