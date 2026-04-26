package fuzs.stoneworks.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;
import fuzs.stoneworks.common.world.block.variant.StoneVariantsProvider;

public class CommonConfig implements ConfigCore {
    @Config(description = "Add vanilla stone block variants to the creative tab provided this mod.")
    public boolean vanillaVariantsInCreativeTab = true;

    @Override
    public void afterConfigReload() {
        StoneVariantsProvider.invalidateItems();
    }
}
