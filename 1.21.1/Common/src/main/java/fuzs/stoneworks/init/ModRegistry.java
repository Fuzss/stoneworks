package fuzs.stoneworks.init;

import fuzs.puzzleslib.api.init.v3.registry.RegistryManager;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.BlockVariant;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.world.level.block.*;

public class ModRegistry {
    static final RegistryManager REGISTRY = RegistryManager.from(Stoneworks.MOD_ID);

    public static void touch() {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            if (variant.blockVariant() == BlockVariant.PILLAR) {
                REGISTRY.registerBlockItem(REGISTRY.registerBlock(variant.name(), () -> new RotatedPillarBlock(variant.baseBlockProperties())));
            } else {
                REGISTRY.registerBlockItem(REGISTRY.registerBlock(variant.name(), () -> new Block(variant.baseBlockProperties())));
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    REGISTRY.registerBlockItem(REGISTRY.registerBlock(variant.stairsName(), () -> new StairBlock(variant.baseBlockState(), variant.baseBlockProperties())));
                    REGISTRY.registerBlockItem(REGISTRY.registerBlock(variant.slabName(), () -> new SlabBlock(variant.baseBlockProperties())));
                    REGISTRY.registerBlockItem(REGISTRY.registerBlock(variant.wallName(), () -> new WallBlock(variant.baseBlockProperties())));
                }
            }
        }
    }
}
