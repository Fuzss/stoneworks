package fuzs.stoneworks;

import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.stoneworks.config.CommonConfig;
import fuzs.stoneworks.init.ModRegistry;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stoneworks implements ModConstructor {
    public static final String MOD_ID = "stoneworks";
    public static final String MOD_NAME = "Stoneworks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).common(CommonConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
