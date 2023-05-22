package fuzs.stoneworks;

import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.CreativeModeTabContext;
import fuzs.puzzleslib.api.item.v2.CreativeModeTabConfigurator;
import fuzs.stoneworks.config.ClientConfig;
import fuzs.stoneworks.init.ModRegistry;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stoneworks implements ModConstructor {
    public static final String MOD_ID = "stoneworks";
    public static final String MOD_NAME = "Stoneworks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID).client(ClientConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.touch();
    }

    @Override
    public void onRegisterCreativeModeTabs(CreativeModeTabContext context) {
        // TODO remove single icon again for 1.19.4, this is handled in puzzles lib then
        context.registerCreativeModeTab(CreativeModeTabConfigurator.from(MOD_ID).icon(() -> new ItemStack(Items.STONE)).icons(StoneVariantsProvider::getDisplayItemStacks).displayItems((featureFlagSet, output, bl) -> {
            output.acceptAll(StoneVariantsProvider.getSortedVariantItems());
        }).withSearchBar());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
