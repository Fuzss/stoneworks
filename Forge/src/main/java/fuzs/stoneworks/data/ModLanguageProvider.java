package fuzs.stoneworks.data;

import com.google.common.collect.Maps;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Map;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String modId) {
        super(gen, modId, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.stoneworks.main", Stoneworks.MOD_NAME);
        Map<Block, String> translations = Maps.newHashMap();
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            variant.addTranslations(translations);
        }
        for (Map.Entry<Block, String> entry : translations.entrySet()) {
            this.add(entry.getKey(), entry.getValue());
        }
    }
}
