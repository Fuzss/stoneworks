package fuzs.stoneworks.data.client;

import com.google.common.collect.Maps;
import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.init.ModRegistry;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder builder) {
        builder.add(ModRegistry.CREATIVE_MODE_TAB.value(), Stoneworks.MOD_NAME);
        Map<Block, String> translations = Maps.newHashMap();
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            variant.addTranslations(translations);
        }
        for (Map.Entry<Block, String> entry : translations.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
    }
}
