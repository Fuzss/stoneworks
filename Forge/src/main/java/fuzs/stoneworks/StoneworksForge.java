package fuzs.stoneworks;

import fuzs.puzzleslib.core.CommonFactories;
import fuzs.stoneworks.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

@Mod(Stoneworks.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class StoneworksForge {

    @SubscribeEvent
    public static void onConstructMod(final FMLConstructModEvent evt) {
        CommonFactories.INSTANCE.modConstructor(Stoneworks.MOD_ID).accept(new Stoneworks());
    }

    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent evt) {
        DataGenerator generator = evt.getGenerator();
        final ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
        generator.addProvider(true, new ModBlockStateProvider(generator, Stoneworks.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModBlockTagsProvider(generator, Stoneworks.MOD_ID, existingFileHelper));
        generator.addProvider(true, new ModLanguageProvider(generator, Stoneworks.MOD_ID));
        generator.addProvider(true, new ModLootTableProvider(generator, Stoneworks.MOD_ID));
        generator.addProvider(true, new ModRecipeProvider(generator));
    }
}
