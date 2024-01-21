package fuzs.stoneworks.data;

import com.google.common.collect.Maps;
import fuzs.puzzleslib.api.data.v1.AbstractLanguageProvider;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.Map;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeModeTab(Stoneworks.MOD_NAME);
        Map<Block, String> translations = Maps.newHashMap();
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            variant.addTranslations(translations);
        }
        for (Map.Entry<Block, String> entry : translations.entrySet()) {
            this.add(entry.getKey(), entry.getValue());
        }
    }
}
