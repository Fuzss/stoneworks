package fuzs.stoneworks.common.data.client;

import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.core.v1.ModContainer;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModRegistry;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.addCreativeModeTab(ModRegistry.CREATIVE_MODE_TAB, Stoneworks.MOD_NAME);
        ModBlockFamilies.getAllFamilies().flatMap((BlockSetFamily family) -> {
            return family.getGeneratedBlockVariants().values().stream();
        }).forEach((Holder.Reference<Block> holder) -> {
            Identifier blockId = holder.key().identifier();
            String blockName = ModContainer.getCapitalizedString(blockId.getPath());
            this.addBlock(holder, blockName);
        });
    }
}
