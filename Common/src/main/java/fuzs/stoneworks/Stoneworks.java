package fuzs.stoneworks;

import fuzs.puzzleslib.core.ModConstructor;
import fuzs.stoneworks.init.ModRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stoneworks implements ModConstructor {
    public static final String MOD_ID = "stoneworks";
    public static final String MOD_NAME = "Stoneworks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
    }
}
