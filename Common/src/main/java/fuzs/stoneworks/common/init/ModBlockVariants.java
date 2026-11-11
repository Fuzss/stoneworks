package fuzs.stoneworks.common.init;

import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.puzzleslib.common.api.init.v3.family.StandaloneBlockSetVariant;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.UnaryOperator;

public final class ModBlockVariants {
    public static final BlockSetVariant REGULAR = new SimpleStoneVariant("regular", (String name) -> {
        return "raw_" + name;
    });
    public static final BlockSetVariant COBBLED = BlockSetVariant.COBBLED;
    public static final BlockSetVariant MOSSY_COBBLED = new SimpleStoneVariant("mossy_cobbled", (String name) -> {
        return "mossy_cobbled_" + name;
    });
    public static final BlockSetVariant BRICKS = BlockSetVariant.BRICKS;
    public static final BlockSetVariant MOSSY_BRICKS = new SimpleStoneVariant("mossy_bricks", (String name) -> {
        return "mossy_" + name + "_bricks";
    });
    public static final BlockSetVariant CRACKED_BRICKS = new SimpleStoneVariant("cracked_bricks", (String name) -> {
        return "cracked_" + name + "_bricks";
    });
    public static final BlockSetVariant POLISHED = BlockSetVariant.POLISHED;
    public static final BlockSetVariant CHISELED = new SimpleStoneVariant("chiseled", (String name) -> {
        return "chiseled_" + name;
    });
    public static final BlockSetVariant TILES = BlockSetVariant.TILES;
    public static final BlockSetVariant CRACKED_TILES = new SimpleStoneVariant("cracked_tiles", (String name) -> {
        return "cracked_" + name + "_tiles";
    });
    public static final BlockSetVariant PILLAR = BlockSetVariant.PILLAR;
    public static final BlockSetVariant SHINGLES = new SimpleStoneVariant("shingles", (String name) -> {
        return name + "_shingles";
    });
    public static final BlockSetVariant PAVERS = new SimpleStoneVariant("pavers", (String name) -> {
        return name + "_pavers";
    });
    public static final BlockSetVariant PLATES = new SimpleStoneVariant("plates", (String name) -> {
        return name + "_plates";
    });
    public static final Map<BlockSetVariant, UnaryOperator<String>> FAMILY_NAMES = Map.of(REGULAR, (String name) -> {
        return "raw_" + name;
    }, COBBLED, (String name) -> {
        return "cobbled_" + name;
    }, MOSSY_COBBLED, (String name) -> {
        return "mossy_cobbled_" + name;
    }, BRICKS, (String name) -> {
        return name + "_brick";
    }, MOSSY_BRICKS, (String name) -> {
        return "mossy_" + name + "_brick";
    }, POLISHED, (String name) -> {
        return "polished_" + name;
    }, TILES, (String name) -> {
        return name + "_tile";
    }, SHINGLES, (String name) -> {
        return name + "_shingle";
    }, PAVERS, (String name) -> {
        return name + "_paver";
    }, PLATES, (String name) -> {
        return name + "_plate";
    });
    public static final List<BlockSetVariant> VALUES = List.of(REGULAR,
            COBBLED,
            MOSSY_COBBLED,
            BRICKS,
            MOSSY_BRICKS,
            CRACKED_BRICKS,
            POLISHED,
            CHISELED,
            TILES,
            CRACKED_TILES,
            PILLAR,
            SHINGLES,
            PAVERS,
            PLATES);
    public static final List<BlockSetVariant> NETHERRACK_VARIANTS = List.of(REGULAR,
            COBBLED,
            MOSSY_COBBLED,
            BRICKS,
            MOSSY_BRICKS,
            CRACKED_BRICKS,
            POLISHED);
    public static final List<BlockSetVariant> NETHER_BRICK_VARIANTS = List.of(CHISELED,
            TILES,
            CRACKED_TILES,
            PILLAR,
            SHINGLES,
            PAVERS,
            PLATES);
    public static final Set<BlockSetVariant> PROVIDES_SHAPE_BLOCKS = Set.of(REGULAR,
            COBBLED,
            MOSSY_COBBLED,
            BRICKS,
            MOSSY_BRICKS,
            POLISHED,
            TILES,
            SHINGLES,
            PAVERS,
            PLATES);
    public static final Set<BlockSetVariant> CRAFTING_VARIANTS = Set.of(SHINGLES, PAVERS, PLATES);
    public static final Map<BlockSetVariant, BlockSetVariant> SMELTING_VARIANTS = Map.of(CRACKED_BRICKS,
            BRICKS,
            CRACKED_TILES,
            TILES);
    public static final Set<BlockSetVariant> STONECUTTER_VARIANTS = Set.of(REGULAR,
            MOSSY_COBBLED,
            MOSSY_BRICKS,
            CRACKED_BRICKS,
            CHISELED,
            CRACKED_TILES,
            SHINGLES,
            PAVERS,
            PLATES);

    private ModBlockVariants() {
        // NO-OP
    }

    private static class SimpleStoneVariant extends StandaloneBlockSetVariant {
        private final UnaryOperator<String> name;

        public SimpleStoneVariant(String name, UnaryOperator<String> nameFunction) {
            super(name);
            this.name = nameFunction;
        }

        @Override
        public void generateFor(BlockSetFamily.Context context, @Nullable String baseNameOverride) {
            Holder.Reference<Block> block = context.getRegistries()
                    .registerBlock(context.getName(this.name, baseNameOverride), () -> {
                        return BlockBehaviour.Properties.ofFullCopy(context.getBaseBlock().value());
                    });
            context.registerBlock(this, block);
            context.registerItem(this, context.getRegistries().registerBlockItem(block));
        }
    }
}
