package fuzs.stoneworks.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;

public class CommonConfig implements ConfigCore {
    @Config(description = "Add vanilla stone block variants to the creative tab provided this mod.")
    public boolean vanillaVariantsInCreativeTab = true;

    @Override
    public void afterConfigReload() {
        StoneVariantsProvider.invalidateItems();
    }
}
