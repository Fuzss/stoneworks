package fuzs.stoneworks.common.config;

import fuzs.puzzleslib.common.api.config.v3.Config;
import fuzs.puzzleslib.common.api.config.v3.ConfigCore;

public class CommonConfig implements ConfigCore {
    @Config(description = "Add vanilla stone block variants to the creative tab for this mod.", worldRestart = true)
    public boolean vanillaVariantsInCreativeTab = true;
}
