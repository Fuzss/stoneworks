package fuzs.stoneworks.common.init;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.Stoneworks;
import fuzs.stoneworks.common.config.CommonConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public final class ModBlockFamilies {
    public static final BlockSetFamily.Writable STONE = stoneFamily(Blocks.STONE,
            "stone").provideFor(ModBlockVariants.REGULAR, Blocks.STONE)
            .provideFor(ModBlockVariants.COBBLED, Blocks.COBBLESTONE)
            .provideFor(ModBlockVariants.MOSSY_COBBLED, Blocks.MOSSY_COBBLESTONE)
            .provideFor(ModBlockVariants.BRICKS, Blocks.STONE_BRICKS)
            .provideFor(ModBlockVariants.MOSSY_BRICKS, Blocks.MOSSY_STONE_BRICKS)
            .provideFor(ModBlockVariants.CRACKED_BRICKS, Blocks.CRACKED_STONE_BRICKS)
            .provideFor(ModBlockVariants.POLISHED, Blocks.SMOOTH_STONE)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_STONE_BRICKS);
    public static final BlockSetFamily.Writable ANDESITE = stoneFamily(Blocks.ANDESITE, "andesite").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.ANDESITE).provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_ANDESITE);
    public static final BlockSetFamily.Writable GRANITE = stoneFamily(Blocks.GRANITE, "granite").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.GRANITE).provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_GRANITE);
    public static final BlockSetFamily.Writable DIORITE = stoneFamily(Blocks.DIORITE, "diorite").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.DIORITE).provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_DIORITE);
    public static final BlockSetFamily.Writable DEEPSLATE = stoneFamily(Blocks.DEEPSLATE, "deepslate").provideFor(
                    ModBlockVariants.REGULAR,
                    Blocks.DEEPSLATE)
            .provideFor(ModBlockVariants.COBBLED, Blocks.COBBLED_DEEPSLATE)
            .provideFor(ModBlockVariants.BRICKS, Blocks.DEEPSLATE_BRICKS)
            .provideFor(ModBlockVariants.CRACKED_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS)
            .provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_DEEPSLATE)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_DEEPSLATE)
            .provideFor(ModBlockVariants.TILES, Blocks.DEEPSLATE_TILES)
            .provideFor(ModBlockVariants.CRACKED_TILES, Blocks.CRACKED_DEEPSLATE_TILES);
    public static final BlockSetFamily.Writable CALCITE = stoneFamily(Blocks.CALCITE, "calcite").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.CALCITE);
    public static final BlockSetFamily.Writable TUFF = stoneFamily(Blocks.TUFF,
            "tuff").provideFor(ModBlockVariants.REGULAR, Blocks.TUFF)
            .provideFor(ModBlockVariants.BRICKS, Blocks.TUFF_BRICKS)
            .provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_TUFF)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_TUFF);
    public static final BlockSetFamily.Writable BASALT = stoneFamily(Blocks.BASALT, "basalt").provideFor(
                    ModBlockVariants.REGULAR,
                    Blocks.BASALT)
            .provideFor(ModBlockVariants.POLISHED, Blocks.SMOOTH_BASALT)
            .provideFor(ModBlockVariants.PILLAR, Blocks.POLISHED_BASALT);
    public static final BlockSetFamily.Writable BLACKSTONE = stoneFamily(Blocks.BLACKSTONE, "blackstone").provideFor(
                    ModBlockVariants.REGULAR,
                    Blocks.BLACKSTONE)
            .provideFor(ModBlockVariants.BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS)
            .provideFor(ModBlockVariants.CRACKED_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .provideFor(ModBlockVariants.POLISHED, Blocks.POLISHED_BLACKSTONE)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_POLISHED_BLACKSTONE);
    public static final BlockSetFamily.Writable NETHERRACK = stoneFamily(Blocks.NETHERRACK, "netherrack").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.NETHERRACK);
    public static final BlockSetFamily.Writable NETHER_BRICK = stoneFamily(Blocks.NETHER_BRICKS,
            "nether_brick").provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_NETHER_BRICKS)
            .provideFor(ModBlockVariants.TILES, Blocks.NETHER_BRICKS)
            .provideFor(ModBlockVariants.CRACKED_TILES, Blocks.CRACKED_NETHER_BRICKS);
    public static final BlockSetFamily.Writable END_STONE = stoneFamily(Blocks.END_STONE, "end_stone").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.END_STONE).provideFor(ModBlockVariants.BRICKS, Blocks.END_STONE_BRICKS);
    public static final BlockSetFamily.Writable PURPUR = stoneFamily(Blocks.PURPUR_BLOCK, "purpur").provideFor(
            ModBlockVariants.PLATES,
            Blocks.PURPUR_BLOCK).provideFor(ModBlockVariants.PILLAR, Blocks.PURPUR_PILLAR);
    public static final BlockSetFamily.Writable PRISMARINE = stoneFamily(Blocks.PRISMARINE, "prismarine").provideFor(
            ModBlockVariants.REGULAR,
            Blocks.PRISMARINE).provideFor(ModBlockVariants.SHINGLES, Blocks.PRISMARINE_BRICKS);
    public static final BlockSetFamily.Writable DARK_PRISMARINE = stoneFamily(Blocks.DARK_PRISMARINE,
            "dark_prismarine").provideFor(ModBlockVariants.PAVERS, Blocks.DARK_PRISMARINE);
    public static final BlockSetFamily.Writable SANDSTONE = stoneFamily(Blocks.SANDSTONE, "sandstone").provideFor(
                    ModBlockVariants.REGULAR,
                    Blocks.SANDSTONE)
            .provideFor(ModBlockVariants.POLISHED, Blocks.CUT_SANDSTONE)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_SANDSTONE);
    public static final BlockSetFamily.Writable RED_SANDSTONE = stoneFamily(Blocks.RED_SANDSTONE,
            "red_sandstone").provideFor(ModBlockVariants.REGULAR, Blocks.RED_SANDSTONE)
            .provideFor(ModBlockVariants.POLISHED, Blocks.CUT_RED_SANDSTONE)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_RED_SANDSTONE);
    public static final BlockSetFamily.Writable QUARTZ = stoneFamily(Blocks.QUARTZ_BLOCK, "quartz").provideFor(
                    ModBlockVariants.REGULAR,
                    Blocks.QUARTZ_BLOCK)
            .provideFor(ModBlockVariants.BRICKS, Blocks.QUARTZ_BRICKS)
            .provideFor(ModBlockVariants.CHISELED, Blocks.CHISELED_QUARTZ_BLOCK)
            .provideFor(ModBlockVariants.PILLAR, Blocks.QUARTZ_PILLAR);
    private static final Set<BlockSetFamily> CHISELED_MOTIF_FAMILIES = Set.of(DIORITE,
            CALCITE,
            PRISMARINE,
            DARK_PRISMARINE);
    private static final ImmutableMap<BlockSetFamily, Map<BlockSetVariant, BlockSetFamily>> ALL_FAMILIES;

    static {
        ImmutableMap.Builder<BlockSetFamily, Map<BlockSetVariant, BlockSetFamily>> allFamilies = ImmutableMap.builder();
        BiConsumer<BlockSetFamily, Map<BlockSetVariant, BlockSetFamily>> registrar = allFamilies::put;
        setupFamily(STONE, ModBlockVariants.VALUES, registrar);
        setupFamily(ANDESITE, ModBlockVariants.VALUES, registrar);
        setupFamily(GRANITE, ModBlockVariants.VALUES, registrar);
        setupFamily(DIORITE, ModBlockVariants.VALUES, registrar);
        setupFamily(DEEPSLATE, ModBlockVariants.VALUES, registrar);
        setupFamily(CALCITE, ModBlockVariants.VALUES, registrar);
        setupFamily(TUFF, ModBlockVariants.VALUES, registrar);
        setupFamily(BASALT, ModBlockVariants.VALUES, registrar);
        setupFamily(BLACKSTONE, ModBlockVariants.VALUES, registrar);
        setupFamily(NETHERRACK, ModBlockVariants.NETHERRACK_VARIANTS, registrar);
        setupFamily(NETHER_BRICK, ModBlockVariants.NETHER_BRICK_VARIANTS, registrar);
        setupFamily(END_STONE, ModBlockVariants.VALUES, registrar);
        setupFamily(PURPUR, ModBlockVariants.VALUES, registrar);
        setupFamily(PRISMARINE, ModBlockVariants.VALUES, registrar);
        setupFamily(DARK_PRISMARINE, ModBlockVariants.VALUES, registrar);
        setupFamily(SANDSTONE, ModBlockVariants.VALUES, registrar);
        setupFamily(RED_SANDSTONE, ModBlockVariants.VALUES, registrar);
        setupFamily(QUARTZ, ModBlockVariants.VALUES, registrar);
        ALL_FAMILIES = allFamilies.build();
    }

    private ModBlockFamilies() {
        // NO-OP
    }

    public static void bootstrap() {
        // NO-OP
    }

    private static BlockSetFamily.Writable stoneFamily(Block baseBlock, String baseName) {
        return BlockSetFamily.base(ModRegistry.REGISTRIES, baseBlock.builtInRegistryHolder(), baseName)
                .configureBlockFamily((BlockFamily.Builder builder) -> {
                    builder.dontGenerateSmeltingRecipe().generateStonecutterRecipe();
                });
    }

    private static void setupFamily(BlockSetFamily.Writable family, List<BlockSetVariant> variants, BiConsumer<BlockSetFamily, Map<BlockSetVariant, BlockSetFamily>> registrar) {
        ImmutableMap.Builder<BlockSetVariant, BlockSetFamily> childFamilies = ImmutableMap.builder();
        for (BlockSetVariant variant : variants) {
            if (!family.getAllBlockVariants().containsKey(variant)) {
                family.generateFor(variant);
            }

            if (ModBlockVariants.PROVIDES_SHAPE_BLOCKS.contains(variant)) {
                childFamilies.put(variant, createChildFamily(family, variant));
            }
        }

        registrar.accept(family, childFamilies.build());
    }

    private static BlockSetFamily createChildFamily(BlockSetFamily family, BlockSetVariant variant) {
        Holder.Reference<Block> mainBlock = family.getBlock(variant);
        BlockSetFamily.Writable childFamily = BlockSetFamily.base(ModRegistry.REGISTRIES,
                        mainBlock,
                        ModBlockVariants.FAMILY_NAMES.get(variant).apply(family.getBaseName()))
                .configureBlockFamily(BlockFamily.Builder::generateStonecutterRecipe);
        if (family.getGeneratedBlockVariants().containsKey(variant)) {
            childFamily.generateFor(BlockSetVariant.STAIRS)
                    .generateFor(BlockSetVariant.SLAB)
                    .generateFor(BlockSetVariant.WALL);
        } else {
            BlockFamily vanillaFamily = BlockFamilies.getFamily(mainBlock.value());
            if (vanillaFamily != null) {
                provideIfPresent(childFamily, BlockSetVariant.STAIRS, vanillaFamily.get(BlockFamily.Variant.STAIRS));
                provideIfPresent(childFamily, BlockSetVariant.SLAB, vanillaFamily.get(BlockFamily.Variant.SLAB));
                provideIfPresent(childFamily, BlockSetVariant.WALL, vanillaFamily.get(BlockFamily.Variant.WALL));
            } else if (mainBlock.value() == Blocks.SMOOTH_STONE) {
                childFamily.provideFor(BlockSetVariant.SLAB, Blocks.SMOOTH_STONE_SLAB);
            }
        }

        return childFamily;
    }

    private static void provideIfPresent(BlockSetFamily.Writable family, BlockSetVariant variant, @Nullable Block block) {
        if (block != null) {
            family.provideFor(variant, block);
        }
    }

    public static Stream<BlockSetFamily> getAllFamilies() {
        return Stream.concat(getBaseFamilies().stream(), getChildFamilies());
    }

    public static Collection<BlockSetFamily> getBaseFamilies() {
        return ALL_FAMILIES.keySet();
    }

    public static Stream<BlockSetFamily> getChildFamilies() {
        return ALL_FAMILIES.values().stream().flatMap((Map<BlockSetVariant, BlockSetFamily> map) -> {
            return map.values().stream();
        });
    }

    public static Stream<BlockSetFamily> getChildFamilies(BlockSetFamily family) {
        return ALL_FAMILIES.getOrDefault(family, ImmutableMap.of()).values().stream();
    }

    @Nullable
    public static BlockSetFamily getChildFamily(BlockSetFamily family, BlockSetVariant variant) {
        Map<BlockSetVariant, BlockSetFamily> childFamilies = ALL_FAMILIES.get(family);
        return childFamilies != null ? childFamilies.get(variant) : null;
    }

    public static boolean hasChiseledMotif(BlockSetFamily family) {
        return CHISELED_MOTIF_FAMILIES.contains(family);
    }

    public static Stream<? extends ItemLike> getSortedVariantItems() {
        boolean includeVanilla = Stoneworks.CONFIG.getHolder(CommonConfig.class).isAvailable() && Stoneworks.CONFIG.get(
                CommonConfig.class).vanillaVariantsInCreativeTab;
        return getBaseFamilies().stream().flatMap((BlockSetFamily family) -> {
            return getVariantItems(family, includeVanilla);
        });
    }

    private static Stream<? extends ItemLike> getVariantItems(BlockSetFamily family, boolean includeVanilla) {
        List<ItemLike> variantItems = new ArrayList<>();
        for (BlockSetVariant variant : ModBlockVariants.VALUES) {
            Holder.Reference<Block> block = family.getBlock(variant);
            if (block == null) {
                continue;
            }

            boolean generated = family.getGeneratedBlockVariants().containsKey(variant);
            if (!generated && !includeVanilla) {
                continue;
            }

            variantItems.add(block.value());
            BlockSetFamily childFamily = getChildFamily(family, variant);
            if (childFamily != null) {
                childFamily.getAllBlockVariants().values().forEach((Holder.Reference<Block> holder) -> {
                    variantItems.add(holder.value());
                });
            }
        }

        return variantItems.stream();
    }
}
