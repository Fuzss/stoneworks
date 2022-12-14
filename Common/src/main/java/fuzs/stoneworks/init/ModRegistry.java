package fuzs.stoneworks.init;

import fuzs.puzzleslib.core.CommonAbstractions;
import fuzs.puzzleslib.core.CommonFactories;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.stoneworks.Stoneworks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CommonFactories.INSTANCE.registration(Stoneworks.MOD_ID);
    private static final CreativeModeTab CREATIVE_MODE_TAB = CommonAbstractions.INSTANCE.creativeTab(Stoneworks.MOD_ID, "main", new Supplier<ItemStack>() {
        @Nullable
        private ItemStack[] itemStacks;

        @Override
        public ItemStack get() {
            if (this.itemStacks == null) {
                this.itemStacks = StoneVariantsProvider.getStoneBlockVariants().stream().filter(variant -> variant.blockVariant() == StoneVariantsProvider.BlockVariant.REGULAR).map(StoneVariantsProvider.StoneBlockVariant::block).map(ItemStack::new).toArray(ItemStack[]::new);
            }
            // stolen from XFactHD, thanks
            int index = (int) (System.currentTimeMillis() / 1200) % this.itemStacks.length;
            return this.itemStacks[index];
        }
    }, false, (List<ItemStack> itemStacks, CreativeModeTab creativeModeTab) -> {
        StoneVariantsProvider.getStoneBlockVariants().stream()
                .sorted(Comparator.<StoneVariantsProvider.StoneBlockVariant>comparingInt(v -> v.stoneType().ordinal()).thenComparingInt(v -> v.blockVariant().ordinal()))
                .flatMap(StoneVariantsProvider.StoneBlockVariant::allBlocks)
                .map(Block::asItem).map(ItemStack::new)
                .forEach(itemStacks::add);
    });

    public static void touch() {
        for (StoneVariantsProvider.StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants()) {
            if (variant.blockVariant() == StoneVariantsProvider.BlockVariant.PILLAR) {
                REGISTRY.registerBlockWithItem(variant.name(), () -> new RotatedPillarBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
            } else {
                REGISTRY.registerBlockWithItem(variant.name(), () -> new Block(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    REGISTRY.registerBlockWithItem(variant.stairsName(), () -> new StairBlock(variant.baseBlockState(), variant.baseBlockProperties()) {}, CREATIVE_MODE_TAB);
                    REGISTRY.registerBlockWithItem(variant.slabName(), () -> new SlabBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                    REGISTRY.registerBlockWithItem(variant.wallName(), () -> new WallBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                }
            }
        }
    }
}
