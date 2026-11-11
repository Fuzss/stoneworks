package fuzs.stoneworks.common.world.block.variant;

import com.google.common.collect.ImmutableMap;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.config.CommonConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StoneVariantsProvider {
    private static final Comparator<StoneBlockVariant> VARIANT_COMPARATOR = Comparator.comparing(StoneBlockVariant::stoneType)
            .thenComparing(StoneBlockVariant::blockVariant);
    private static final Map<String, StoneBlockVariant> STONE_BLOCK_VARIANTS;

    static {
        ImmutableMap.Builder<String, StoneBlockVariant> builder = ImmutableMap.builder();
        Consumer<StoneBlockVariant> registrar = (StoneBlockVariant variant) -> {
            builder.put(variant.name(), variant);
        };
        registerAllStoneBlockVariants(registrar);
        builder.buildOrThrow();
        registerVanillaOverrides(registrar);
        STONE_BLOCK_VARIANTS = builder.orderEntriesByValue(VARIANT_COMPARATOR).buildKeepingLast();
    }

    public static Stream<StoneBlockVariant> getAllStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream();
    }

    public static Stream<StoneBlockVariant> getStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream().filter(Predicate.not(StoneBlockVariant::isVanillaVariant));
    }

    public static Stream<? extends ItemLike> getSortedVariantItems() {
        return (Stoneworks.CONFIG.getHolder(CommonConfig.class).isAvailable()
                && Stoneworks.CONFIG.get(CommonConfig.class).vanillaVariantsInCreativeTab ? getAllStoneBlockVariants() :
                getStoneBlockVariants()).sorted(VARIANT_COMPARATOR).flatMap(StoneBlockVariant::allBlocks);
    }

    public static StoneBlockVariant getStoneVariant(StoneType stoneType, BlockVariant blockVariant) {
        return STONE_BLOCK_VARIANTS.get(blockVariant.getName(stoneType));
    }

    private static void registerAllStoneBlockVariants(Consumer<StoneBlockVariant> registrar) {
        for (StoneType stoneType : StoneType.values()) {
            for (BlockVariant blockVariant : BlockVariant.values()) {
                registrar.accept(new StoneBlockVariant(stoneType, blockVariant));
            }
        }
    }

    private static void registerVanillaOverrides(Consumer<StoneBlockVariant> registrar) {
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE, BlockVariant.REGULAR, Blocks.STONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE, BlockVariant.COBBLED, Blocks.COBBLESTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE,
                BlockVariant.MOSSY_COBBLED,
                Blocks.MOSSY_COBBLESTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE, BlockVariant.BRICKS, Blocks.STONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE,
                BlockVariant.MOSSY_BRICKS,
                Blocks.MOSSY_STONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE,
                BlockVariant.CRACKED_BRICKS,
                Blocks.CRACKED_STONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE, BlockVariant.POLISHED, Blocks.SMOOTH_STONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.STONE,
                BlockVariant.CHISELED,
                Blocks.CHISELED_STONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.ANDESITE, BlockVariant.REGULAR, Blocks.ANDESITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.ANDESITE,
                BlockVariant.POLISHED,
                Blocks.POLISHED_ANDESITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.GRANITE, BlockVariant.REGULAR, Blocks.GRANITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.GRANITE,
                BlockVariant.POLISHED,
                Blocks.POLISHED_GRANITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DIORITE, BlockVariant.REGULAR, Blocks.DIORITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DIORITE,
                BlockVariant.POLISHED,
                Blocks.POLISHED_DIORITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE, BlockVariant.REGULAR, Blocks.DEEPSLATE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.COBBLED,
                Blocks.COBBLED_DEEPSLATE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.BRICKS,
                Blocks.DEEPSLATE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.CRACKED_BRICKS,
                Blocks.CRACKED_DEEPSLATE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.POLISHED,
                Blocks.POLISHED_DEEPSLATE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.CHISELED,
                Blocks.CHISELED_DEEPSLATE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE, BlockVariant.TILES, Blocks.DEEPSLATE_TILES));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DEEPSLATE,
                BlockVariant.CRACKED_TILES,
                Blocks.CRACKED_DEEPSLATE_TILES));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.CALCITE, BlockVariant.REGULAR, Blocks.CALCITE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.TUFF, BlockVariant.REGULAR, Blocks.TUFF));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.TUFF, BlockVariant.BRICKS, Blocks.TUFF_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.TUFF, BlockVariant.POLISHED, Blocks.POLISHED_TUFF));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.TUFF, BlockVariant.CHISELED, Blocks.CHISELED_TUFF));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BASALT, BlockVariant.REGULAR, Blocks.BASALT));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BASALT, BlockVariant.POLISHED, Blocks.SMOOTH_BASALT));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BASALT, BlockVariant.PILLAR, Blocks.POLISHED_BASALT));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BLACKSTONE, BlockVariant.REGULAR, Blocks.BLACKSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BLACKSTONE,
                BlockVariant.BRICKS,
                Blocks.POLISHED_BLACKSTONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BLACKSTONE,
                BlockVariant.CRACKED_BRICKS,
                Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BLACKSTONE,
                BlockVariant.POLISHED,
                Blocks.POLISHED_BLACKSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.BLACKSTONE,
                BlockVariant.CHISELED,
                Blocks.CHISELED_POLISHED_BLACKSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.NETHERRACK, BlockVariant.REGULAR, Blocks.NETHERRACK));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.NETHERRACK,
                BlockVariant.CHISELED,
                Blocks.CHISELED_NETHER_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.NETHERRACK, BlockVariant.TILES, Blocks.NETHER_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.NETHERRACK,
                BlockVariant.CRACKED_TILES,
                Blocks.CRACKED_NETHER_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.END_STONE, BlockVariant.REGULAR, Blocks.END_STONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.END_STONE,
                BlockVariant.BRICKS,
                Blocks.END_STONE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.PURPUR,
                BlockVariant.PLATES,
                Blocks.PURPUR_BLOCK,
                Blocks.PURPUR_STAIRS,
                Blocks.PURPUR_SLAB));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.PURPUR, BlockVariant.PILLAR, Blocks.PURPUR_PILLAR));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.PRISMARINE, BlockVariant.REGULAR, Blocks.PRISMARINE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.PRISMARINE,
                BlockVariant.SHINGLES,
                Blocks.PRISMARINE_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.DARK_PRISMARINE,
                BlockVariant.PAVERS,
                Blocks.DARK_PRISMARINE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.SANDSTONE, BlockVariant.REGULAR, Blocks.SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.SANDSTONE,
                BlockVariant.POLISHED,
                Blocks.CUT_SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.SANDSTONE,
                BlockVariant.CHISELED,
                Blocks.CHISELED_SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.RED_SANDSTONE,
                BlockVariant.REGULAR,
                Blocks.RED_SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.RED_SANDSTONE,
                BlockVariant.POLISHED,
                Blocks.CUT_RED_SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.RED_SANDSTONE,
                BlockVariant.CHISELED,
                Blocks.CHISELED_RED_SANDSTONE));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.QUARTZ, BlockVariant.REGULAR, Blocks.QUARTZ_BLOCK));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.QUARTZ,
                BlockVariant.CHISELED,
                Blocks.CHISELED_QUARTZ_BLOCK));
        registrar.accept(new VanillaStoneBlockVariant(StoneType.QUARTZ, BlockVariant.PILLAR, Blocks.QUARTZ_PILLAR));
    }

    private static class VanillaStoneBlockVariant extends StoneBlockVariant {
        private final String block;
        private final boolean hasCustomName;

        VanillaStoneBlockVariant(StoneType stoneType, BlockVariant blockVariant, Block... blocks) {
            super(stoneType, blockVariant, Arrays.copyOf(blocks, 4));
            if (blocks.length < 1 || blocks.length > 4) {
                throw new IllegalStateException("Wrong number of blocks provided!");
            }
            this.block = BuiltInRegistries.BLOCK.getKey(blocks[0]).getPath();
            this.hasCustomName = !this.blockName().equals(this.name());
        }

        @Override
        public String blockName() {
            return this.block;
        }

        @Override
        public String stairsName() {
            return this.hasCustomName ? this.blockName() + "_stairs" : super.stairsName();
        }

        @Override
        public String slabName() {
            return this.hasCustomName ? this.blockName() + "_slab" : super.slabName();
        }

        @Override
        public String wallName() {
            return this.hasCustomName ? this.blockName() + "_wall" : super.wallName();
        }

        @Override
        public Identifier id(String key) {
            return Identifier.withDefaultNamespace(key);
        }

        @Override
        public boolean isVanillaVariant() {
            return true;
        }
    }
}
