package fuzs.stoneworks.init;

import fuzs.puzzleslib.core.CommonAbstractions;
import fuzs.puzzleslib.core.CommonFactories;
import fuzs.puzzleslib.init.RegistryManager;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.BlockVariant;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WallBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModRegistry {
    private static final RegistryManager REGISTRY = CommonFactories.INSTANCE.registration(Stoneworks.MOD_ID);
    private static final CreativeModeTab CREATIVE_MODE_TAB = CommonAbstractions.INSTANCE.creativeModeTabBuilder(Stoneworks.MOD_ID, "main").setIcon(new Supplier<>() {
        @Nullable
        private ItemStack[] itemStacks;

        @Override
        public ItemStack get() {
            if (this.itemStacks == null) {
                this.itemStacks = StoneVariantsProvider.getAllStoneBlockVariants().filter(variant -> variant.blockVariant() == BlockVariant.REGULAR).map(StoneBlockVariant::block).map(ItemStack::new).toArray(ItemStack[]::new);
            }
            // stolen from XFactHD, thanks :)
            int index = (int) (System.currentTimeMillis() / 2000) % this.itemStacks.length;
            return this.itemStacks[index];
        }
    }).disableIconCache().appendItems((List<ItemStack> itemStacks, CreativeModeTab creativeModeTab) -> {
        itemStacks.addAll(StoneVariantsProvider.getSortedVariantItems());
    }).showSearch().build();

    public static void touch() {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            if (variant.blockVariant() == BlockVariant.PILLAR) {
                REGISTRY.registerBlockWithItem(variant.name(), () -> new RotatedPillarBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
            } else {
                REGISTRY.registerBlockWithItem(variant.name(), () -> new Block(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                if (variant.blockVariant().supportsAdditionalBlocks()) {
                    REGISTRY.registerBlockWithItem(variant.stairsName(), () -> CommonAbstractions.INSTANCE.stairBlock(variant::baseBlockState, variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                    REGISTRY.registerBlockWithItem(variant.slabName(), () -> new SlabBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                    REGISTRY.registerBlockWithItem(variant.wallName(), () -> new WallBlock(variant.baseBlockProperties()), CREATIVE_MODE_TAB);
                }
            }
        }
    }
}
