package fuzs.stoneworks.world.block.variant;

import com.google.common.collect.Maps;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.config.ClientConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StoneVariantsProvider {
    private static final Map<String, StoneBlockVariant> STONE_BLOCK_VARIANTS = Maps.newHashMap();
    @Nullable
    private static Collection<ItemStack> sortedVariantItems;

    static {
        registerAllStoneBlockVariants();
        registerVanillaOverrides();
    }

    public static void invalidateItems() {
        sortedVariantItems = null;
    }

    public static Stream<StoneBlockVariant> getAllStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream();
    }

    public static Stream<StoneBlockVariant> getStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream().filter(Predicate.not(StoneBlockVariant::isVanillaVariant));
    }

    public static Collection<ItemStack> getSortedVariantItems() {
        if (sortedVariantItems == null) {
            Stream<StoneBlockVariant> stream = Stoneworks.CONFIG.get(ClientConfig.class).vanillaVariantsInCreativeTab ? getAllStoneBlockVariants() : getStoneBlockVariants();
            sortedVariantItems = stream.sorted(Comparator.<StoneBlockVariant>comparingInt(v -> v.stoneType().ordinal()).thenComparingInt(v -> v.blockVariant().ordinal()))
                    .flatMap(StoneBlockVariant::allBlocks)
                    .map(Block::asItem).map(ItemStack::new).toList();
        }
        return sortedVariantItems;
    }

    private static void registerAllStoneBlockVariants() {
        for (StoneType stoneType : StoneType.values()) {
            for (BlockVariant blockVariant : BlockVariant.values()) {
                registerStoneBlockVariant(new StoneBlockVariant(stoneType, blockVariant), false);
            }
        }
    }

    private static void registerStoneBlockVariant(StoneBlockVariant variant, boolean replace) {
        if (STONE_BLOCK_VARIANTS.put(variant.name(), variant) == null && replace) {
            throw new IllegalStateException("unable to replace stone block variant %s".formatted(variant.name()));
        }
    }

    private static void registerVanillaOverrides() {
        registerVanillaOverride(Blocks.STONE, StoneType.STONE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.COBBLESTONE, StoneType.STONE, BlockVariant.COBBLED);
        registerVanillaOverride(Blocks.MOSSY_COBBLESTONE, StoneType.STONE, BlockVariant.MOSSY_COBBLED);
        registerVanillaOverride(Blocks.STONE_BRICKS, StoneType.STONE, BlockVariant.BRICKS);
        registerVanillaOverride(Blocks.MOSSY_STONE_BRICKS, StoneType.STONE, BlockVariant.MOSSY_BRICKS);
        registerVanillaOverride(Blocks.CRACKED_STONE_BRICKS, StoneType.STONE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride(Blocks.SMOOTH_STONE, StoneType.STONE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.CHISELED_STONE_BRICKS, StoneType.STONE, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.ANDESITE, StoneType.ANDESITE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.POLISHED_ANDESITE, StoneType.ANDESITE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.GRANITE, StoneType.GRANITE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.POLISHED_GRANITE, StoneType.GRANITE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.DIORITE, StoneType.DIORITE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.POLISHED_DIORITE, StoneType.DIORITE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.DEEPSLATE, StoneType.DEEPSLATE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.COBBLED_DEEPSLATE, StoneType.DEEPSLATE, BlockVariant.COBBLED);
        registerVanillaOverride(Blocks.DEEPSLATE_BRICKS, StoneType.DEEPSLATE, BlockVariant.BRICKS);
        registerVanillaOverride(Blocks.CRACKED_DEEPSLATE_BRICKS, StoneType.DEEPSLATE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride(Blocks.POLISHED_DEEPSLATE, StoneType.DEEPSLATE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.CHISELED_DEEPSLATE, StoneType.DEEPSLATE, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.DEEPSLATE_TILES, StoneType.DEEPSLATE, BlockVariant.TILES);
        registerVanillaOverride(Blocks.CRACKED_DEEPSLATE_TILES, StoneType.DEEPSLATE, BlockVariant.CRACKED_TILES);
        registerVanillaOverride(Blocks.CALCITE, StoneType.CALCITE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.TUFF, StoneType.TUFF, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.BASALT, StoneType.BASALT, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.SMOOTH_BASALT, StoneType.BASALT, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.POLISHED_BASALT, StoneType.BASALT, BlockVariant.PILLAR);
        registerVanillaOverride(Blocks.BLACKSTONE, StoneType.BLACKSTONE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.POLISHED_BLACKSTONE_BRICKS, StoneType.BLACKSTONE, BlockVariant.BRICKS);
        registerVanillaOverride(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, StoneType.BLACKSTONE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride(Blocks.POLISHED_BLACKSTONE, StoneType.BLACKSTONE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.CHISELED_POLISHED_BLACKSTONE, StoneType.BLACKSTONE, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.NETHERRACK, StoneType.NETHERRACK, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.CHISELED_NETHER_BRICKS, StoneType.NETHERRACK, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.NETHER_BRICKS, StoneType.NETHERRACK, BlockVariant.TILES);
        registerVanillaOverride(Blocks.CRACKED_NETHER_BRICKS, StoneType.NETHERRACK, BlockVariant.CRACKED_TILES);
        registerVanillaOverride(Blocks.END_STONE, StoneType.END_STONE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.END_STONE_BRICKS, StoneType.END_STONE, BlockVariant.BRICKS);
        registerVanillaOverride(Blocks.PURPUR_BLOCK, StoneType.PURPUR, BlockVariant.PLATES);
        registerVanillaOverride(Blocks.PURPUR_PILLAR, StoneType.PURPUR, BlockVariant.PILLAR);
        registerVanillaOverride(Blocks.PRISMARINE, StoneType.PRISMARINE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.PRISMARINE_BRICKS, StoneType.PRISMARINE, BlockVariant.SHINGLES);
        registerVanillaOverride(Blocks.DARK_PRISMARINE, StoneType.DARK_PRISMARINE, BlockVariant.PAVERS);
        registerVanillaOverride(Blocks.SANDSTONE, StoneType.SANDSTONE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.CUT_SANDSTONE, StoneType.SANDSTONE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.CHISELED_SANDSTONE, StoneType.SANDSTONE, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.RED_SANDSTONE, StoneType.RED_SANDSTONE, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.CUT_RED_SANDSTONE, StoneType.RED_SANDSTONE, BlockVariant.POLISHED);
        registerVanillaOverride(Blocks.CHISELED_RED_SANDSTONE, StoneType.RED_SANDSTONE, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.QUARTZ_BLOCK, StoneType.QUARTZ, BlockVariant.REGULAR);
        registerVanillaOverride(Blocks.QUARTZ_BRICKS, StoneType.QUARTZ, BlockVariant.BRICKS);
        registerVanillaOverride(Blocks.CHISELED_QUARTZ_BLOCK, StoneType.QUARTZ, BlockVariant.CHISELED);
        registerVanillaOverride(Blocks.QUARTZ_PILLAR, StoneType.QUARTZ, BlockVariant.PILLAR);
    }

    private static void registerVanillaOverride(Block block, StoneType stoneType, BlockVariant blockVariant) {
        registerStoneBlockVariant(new VanillaStoneBlockVariant(block, stoneType, blockVariant), true);
    }

    private static class VanillaStoneBlockVariant extends StoneBlockVariant {
        private final String block;
        private final boolean deviates;

        VanillaStoneBlockVariant(Block block, StoneType stoneType, BlockVariant blockVariant) {
            super(stoneType, blockVariant);
            this.block = Registry.BLOCK.getKey(block).getPath();
            this.deviates = !this.blockName().equals(this.name());
        }

        @Override
        public String blockName() {
            return this.block;
        }

        @Override
        public String stairsName() {
            return this.deviates ? this.blockName() + "_stairs" : super.stairsName();
        }

        @Override
        public String slabName() {
            return this.deviates ? this.blockName() + "_slab" : super.slabName();
        }

        @Override
        public String wallName() {
            return this.deviates ? this.blockName() + "_wall" : super.wallName();
        }

        @Override
        protected ResourceLocation id(String key) {
            return new ResourceLocation(key);
        }

        @Override
        public boolean isVanillaVariant() {
            return true;
        }
    }
}
