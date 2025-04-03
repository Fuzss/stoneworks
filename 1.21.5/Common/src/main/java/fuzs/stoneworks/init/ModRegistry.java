package fuzs.stoneworks.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.BlockVariant;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModRegistry {
    static final RegistryManager REGISTRIES = RegistryManager.from(Stoneworks.MOD_ID);
    public static final Holder.Reference<CreativeModeTab> CREATIVE_MODE_TAB = REGISTRIES.registerCreativeModeTab("main",
            () -> new ItemStack(Items.STONE),
            (CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) -> {
                output.acceptAll(StoneVariantsProvider.getSortedVariantItems());
            },
            true);

    public static void bootstrap() {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            if (variant.blockVariant() == BlockVariant.PILLAR) {
                REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(variant.name(),
                        RotatedPillarBlock::new,
                        variant::baseBlockProperties));
            } else {
                REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(variant.name(), variant::baseBlockProperties));
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(variant.stairsName(),
                            (BlockBehaviour.Properties properties) -> new StairBlock(variant.baseBlockState(),
                                    properties),
                            variant::baseBlockProperties));
                    REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(variant.slabName(),
                            SlabBlock::new,
                            variant::baseBlockProperties));
                    REGISTRIES.registerBlockItem(REGISTRIES.registerBlock(variant.wallName(),
                            WallBlock::new,
                            variant::baseBlockProperties));
                }
            }
        }
    }
}
