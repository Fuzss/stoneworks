package fuzs.stoneworks.world.block.variant;

import com.google.common.collect.Maps;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.config.ClientConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StoneVariantsProvider {
    private static final Map<String, StoneBlockVariant> STONE_BLOCK_VARIANTS = Maps.newLinkedHashMap();
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

    public static ItemStack[] getDisplayItemStacks() {
        return StoneVariantsProvider.getAllStoneBlockVariants().filter(variant -> variant.blockVariant() == BlockVariant.REGULAR).map(StoneBlockVariant::block).map(ItemStack::new).toArray(ItemStack[]::new);
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

    public static StoneBlockVariant getStoneVariant(StoneType stoneType, BlockVariant blockVariant) {
        return STONE_BLOCK_VARIANTS.get(blockVariant.getName(stoneType));
    }

    private static void registerVanillaOverrides() {
        registerVanillaOverride(StoneType.STONE, BlockVariant.REGULAR, Blocks.STONE);
        registerVanillaOverride(StoneType.STONE, BlockVariant.COBBLED, Blocks.COBBLESTONE);
        registerVanillaOverride(StoneType.STONE, BlockVariant.MOSSY_COBBLED, Blocks.MOSSY_COBBLESTONE);
        registerVanillaOverride(StoneType.STONE, BlockVariant.BRICKS, Blocks.STONE_BRICKS);
        registerVanillaOverride(StoneType.STONE, BlockVariant.MOSSY_BRICKS, Blocks.MOSSY_STONE_BRICKS);
        registerVanillaOverride(StoneType.STONE, BlockVariant.CRACKED_BRICKS, Blocks.CRACKED_STONE_BRICKS);
        registerVanillaOverride(StoneType.STONE, BlockVariant.POLISHED, Blocks.SMOOTH_STONE);
        registerVanillaOverride(StoneType.STONE, BlockVariant.CHISELED, Blocks.CHISELED_STONE_BRICKS);
        registerVanillaOverride(StoneType.ANDESITE, BlockVariant.REGULAR, Blocks.ANDESITE);
        registerVanillaOverride(StoneType.ANDESITE, BlockVariant.POLISHED, Blocks.POLISHED_ANDESITE);
        registerVanillaOverride(StoneType.GRANITE, BlockVariant.REGULAR, Blocks.GRANITE);
        registerVanillaOverride(StoneType.GRANITE, BlockVariant.POLISHED, Blocks.POLISHED_GRANITE);
        registerVanillaOverride(StoneType.DIORITE, BlockVariant.REGULAR, Blocks.DIORITE);
        registerVanillaOverride(StoneType.DIORITE, BlockVariant.POLISHED, Blocks.POLISHED_DIORITE);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.REGULAR, Blocks.DEEPSLATE);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.COBBLED, Blocks.COBBLED_DEEPSLATE);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.BRICKS, Blocks.DEEPSLATE_BRICKS);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.CRACKED_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.POLISHED, Blocks.POLISHED_DEEPSLATE);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.CHISELED, Blocks.CHISELED_DEEPSLATE);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.TILES, Blocks.DEEPSLATE_TILES);
        registerVanillaOverride(StoneType.DEEPSLATE, BlockVariant.CRACKED_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
        registerVanillaOverride(StoneType.CALCITE, BlockVariant.REGULAR, Blocks.CALCITE);
        registerVanillaOverride(StoneType.TUFF, BlockVariant.REGULAR, Blocks.TUFF);
        registerVanillaOverride(StoneType.BASALT, BlockVariant.REGULAR, Blocks.BASALT);
        registerVanillaOverride(StoneType.BASALT, BlockVariant.POLISHED, Blocks.SMOOTH_BASALT);
        registerVanillaOverride(StoneType.BASALT, BlockVariant.PILLAR, Blocks.POLISHED_BASALT);
        registerVanillaOverride(StoneType.BLACKSTONE, BlockVariant.REGULAR, Blocks.BLACKSTONE);
        registerVanillaOverride(StoneType.BLACKSTONE, BlockVariant.BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS);
        registerVanillaOverride(StoneType.BLACKSTONE, BlockVariant.CRACKED_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerVanillaOverride(StoneType.BLACKSTONE, BlockVariant.POLISHED, Blocks.POLISHED_BLACKSTONE);
        registerVanillaOverride(StoneType.BLACKSTONE, BlockVariant.CHISELED, Blocks.CHISELED_POLISHED_BLACKSTONE);
        registerVanillaOverride(StoneType.NETHERRACK, BlockVariant.REGULAR, Blocks.NETHERRACK);
        registerVanillaOverride(StoneType.NETHERRACK, BlockVariant.CHISELED, Blocks.CHISELED_NETHER_BRICKS);
        registerVanillaOverride(StoneType.NETHERRACK, BlockVariant.TILES, Blocks.NETHER_BRICKS);
        registerVanillaOverride(StoneType.NETHERRACK, BlockVariant.CRACKED_TILES, Blocks.CRACKED_NETHER_BRICKS);
        registerVanillaOverride(StoneType.END_STONE, BlockVariant.REGULAR, Blocks.END_STONE);
        registerVanillaOverride(StoneType.END_STONE, BlockVariant.BRICKS, Blocks.END_STONE_BRICKS);
        registerVanillaOverride(StoneType.PURPUR, BlockVariant.PLATES, Blocks.PURPUR_BLOCK, Blocks.PURPUR_STAIRS, Blocks.PURPUR_SLAB);
        registerVanillaOverride(StoneType.PURPUR, BlockVariant.PILLAR, Blocks.PURPUR_PILLAR);
        registerVanillaOverride(StoneType.PRISMARINE, BlockVariant.REGULAR, Blocks.PRISMARINE);
        registerVanillaOverride(StoneType.PRISMARINE, BlockVariant.SHINGLES, Blocks.PRISMARINE_BRICKS);
        registerVanillaOverride(StoneType.DARK_PRISMARINE, BlockVariant.PAVERS, Blocks.DARK_PRISMARINE);
        registerVanillaOverride(StoneType.SANDSTONE, BlockVariant.REGULAR, Blocks.SANDSTONE);
        registerVanillaOverride(StoneType.SANDSTONE, BlockVariant.POLISHED, Blocks.CUT_SANDSTONE);
        registerVanillaOverride(StoneType.SANDSTONE, BlockVariant.CHISELED, Blocks.CHISELED_SANDSTONE);
        registerVanillaOverride(StoneType.RED_SANDSTONE, BlockVariant.REGULAR, Blocks.RED_SANDSTONE);
        registerVanillaOverride(StoneType.RED_SANDSTONE, BlockVariant.POLISHED, Blocks.CUT_RED_SANDSTONE);
        registerVanillaOverride(StoneType.RED_SANDSTONE, BlockVariant.CHISELED, Blocks.CHISELED_RED_SANDSTONE);
        registerVanillaOverride(StoneType.QUARTZ, BlockVariant.REGULAR, Blocks.QUARTZ_BLOCK);
        registerVanillaOverride(StoneType.QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS);
        registerVanillaOverride(StoneType.QUARTZ, BlockVariant.CHISELED, Blocks.CHISELED_QUARTZ_BLOCK);
        registerVanillaOverride(StoneType.QUARTZ, BlockVariant.PILLAR, Blocks.QUARTZ_PILLAR);
    }

    private static void registerVanillaOverride(StoneType stoneType, BlockVariant blockVariant, Block... blocks) {
        registerStoneBlockVariant(new VanillaStoneBlockVariant(stoneType, blockVariant, blocks), true);
    }

    private static class VanillaStoneBlockVariant extends StoneBlockVariant {
        private final String block;
        private final boolean deviates;

        VanillaStoneBlockVariant(StoneType stoneType, BlockVariant blockVariant, Block... blocks) {
            super(stoneType, blockVariant, Arrays.copyOf(blocks, 4));
            if (blocks.length < 1 || blocks.length > 4) throw new IllegalStateException("wrong number of blocks provided");
            this.block = BuiltInRegistries.BLOCK.getKey(blocks[0]).getPath();
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
        public ResourceLocation id(String key) {
            return new ResourceLocation(key);
        }

        @Override
        public boolean isVanillaVariant() {
            return true;
        }
    }
}
