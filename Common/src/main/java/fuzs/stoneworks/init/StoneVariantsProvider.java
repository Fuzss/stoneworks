package fuzs.stoneworks.init;

import com.google.common.collect.Maps;
import fuzs.stoneworks.Stoneworks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

public class StoneVariantsProvider {
    private static final Map<String, StoneBlockVariant> STONE_BLOCK_VARIANTS = Maps.newHashMap();
    @Nullable
    private static Collection<ItemStack> sortedVariantItems;

    static {
        registerAllStoneBlockVariants();
        registerVanillaOverrides();
    }

    public static Stream<StoneBlockVariant> getAllStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream();
    }

    public static Collection<StoneBlockVariant> getStoneBlockVariants() {
        return STONE_BLOCK_VARIANTS.values().stream().filter(variant -> !(variant instanceof VanillaStoneBlockVariant)).toList();
    }

    public static Collection<ItemStack> getSortedVariantItems() {
        if (sortedVariantItems == null) {
            sortedVariantItems = STONE_BLOCK_VARIANTS.values().stream()
                    .sorted(Comparator.<StoneBlockVariant>comparingInt(v -> v.stoneType().ordinal()).thenComparingInt(v -> v.blockVariant().ordinal()))
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

    public static class StoneBlockVariant {
        private final StoneType stoneType;
        private final BlockVariant blockVariant;
        private final Block[] blocks = new Block[4];

        public StoneBlockVariant(StoneType stoneType, BlockVariant blockVariant) {
            this.stoneType = stoneType;
            this.blockVariant = blockVariant;
        }

        public StoneType stoneType() {
            return this.stoneType;
        }

        public BlockVariant blockVariant() {
            return this.blockVariant;
        }

        public final String name() {
            return this.blockVariant.getName(this.stoneType);
        }

        public String blockName() {
            return this.name();
        }

        public String stairsName() {
            return this.blockVariant.getAdditionalName(this.stoneType, "stairs");
        }

        public String slabName() {
            return this.blockVariant.getAdditionalName(this.stoneType, "slab");
        }

        public String wallName() {
            return this.blockVariant.getAdditionalName(this.stoneType, "wall");
        }

        public void addTranslations(Map<Block, String> translations) {
            translations.put(this.block(), convertLowerUnderscoreToText(this.name()));
            if (this.blockVariant.supportsAdditionalBlocks()) {
                translations.put(this.stairs(), convertLowerUnderscoreToText(this.stairsName()));
                translations.put(this.slab(), convertLowerUnderscoreToText(this.slabName()));
                translations.put(this.wall(), convertLowerUnderscoreToText(this.wallName()));
            }
        }

        private static String convertLowerUnderscoreToText(String lowerUnderscore) {
            String[] strings = lowerUnderscore.split("_");
            StringJoiner joiner = new StringJoiner(" ");
            for (String string : strings) {
                joiner.add(Character.toUpperCase(string.charAt(0)) + (string.length() > 1 ? string.substring(1) : ""));
            }
            return joiner.toString();
        }

        public Stream<Block> allBlocks() {
            return Stream.of(this.block(), this.stairs(), this.slab(), this.wall()).filter(Objects::nonNull);
        }

        @NotNull
        public Block block() {
            return Objects.requireNonNull(this.block(0, this.blockName()), "base block was null");
        }

        @Nullable
        public Block stairs() {
            return this.block(1, this.stairsName());
        }

        @Nullable
        public Block slab() {
            return this.block(2, this.slabName());
        }

        @Nullable
        public Block wall() {
            return this.block(3, this.wallName());
        }

        @Nullable
        private Block block(int index, String key) {
            if (this.blocks[index] == null && (this.isVanillaVariant() || index == 0 || this.blockVariant.supportsAdditionalBlocks())) {
                ResourceLocation id = this.id(key);
                if (!Registry.BLOCK.containsKey(id)) {
                    if (this.isVanillaVariant()) {
                        // stupid hack so this does not run every time when the vanilla variant simply doesn't have the block type
                        // could also just let the call to Registry.BLOCK::get go through, as air is default entry,
                        // but not trusting that with all the registry stuff Forge does
                        this.blocks[index] = Blocks.AIR;
                    } else {
                        throw new IllegalArgumentException("%s is not a valid block".formatted(id));
                    }
                } else {
                    this.blocks[index] = Registry.BLOCK.get(id);
                }
            }
            return this.blocks[index] == Blocks.AIR ? null : this.blocks[index];
        }

        protected ResourceLocation id(String key) {
            return new ResourceLocation(Stoneworks.MOD_ID, key);
        }

        public BlockState baseBlockState() {
            return this.stoneType.getDefaultBlockState();
        }

        public BlockBehaviour.Properties baseBlockProperties() {
            return this.stoneType.getBlockProperties(this.blockVariant);
        }

        public boolean isVanillaVariant() {
            return false;
        }
    }

    private static class VanillaStoneBlockVariant extends StoneBlockVariant {
        private final String block;
        private final boolean deviates;

        public VanillaStoneBlockVariant(Block block, StoneType stoneType, BlockVariant blockVariant) {
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

    public enum StoneType {
        STONE(Blocks.STONE), ANDESITE(Blocks.ANDESITE), GRANITE(Blocks.GRANITE), DIORITE(Blocks.DIORITE), DEEPSLATE(Blocks.DEEPSLATE), CALCITE(Blocks.CALCITE), TUFF(Blocks.TUFF), BASALT(Blocks.BASALT), BLACKSTONE(Blocks.BLACKSTONE), NETHERRACK(Blocks.NETHERRACK), END_STONE(Blocks.END_STONE), PURPUR(Blocks.PURPUR_BLOCK), PRISMARINE(Blocks.PRISMARINE), DARK_PRISMARINE(Blocks.DARK_PRISMARINE), SANDSTONE(Blocks.SANDSTONE), RED_SANDSTONE(Blocks.RED_SANDSTONE), QUARTZ(Blocks.QUARTZ_BLOCK);

        private final Block baseBlock;

        StoneType(Block baseBlock) {
            this.baseBlock = baseBlock;
        }

        private BlockState getDefaultBlockState() {
            return this.baseBlock.defaultBlockState();
        }

        private BlockBehaviour.Properties getBlockProperties(BlockVariant blockVariant) {
            if (this == NETHERRACK && blockVariant.ordinal() >= BlockVariant.CHISELED.ordinal()) {
                return BlockBehaviour.Properties.copy(Blocks.NETHER_BRICKS);
            }
            BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(this.baseBlock);
            if (blockVariant != BlockVariant.REGULAR) {
                properties.strength(this.baseBlock.defaultDestroyTime() + 0.5F, this.baseBlock.getExplosionResistance());
            }
            return properties;
        }

        public String getName() {
            return this.name().toLowerCase(Locale.ROOT);
        }

        public boolean hasChiseledMotif() {
            // no basalt it looks bad, also vanilla variants are ignored anyway
            return this == DIORITE || this == DEEPSLATE || this == CALCITE || this == TUFF || this == BLACKSTONE || this == NETHERRACK || this == PRISMARINE || this == DARK_PRISMARINE || this == SANDSTONE || this == RED_SANDSTONE;
        }
    }

    public enum BlockVariant {
        REGULAR("%s"), COBBLED("cobbled_%s"), MOSSY_COBBLED("mossy_cobbled_%s"), BRICKS("%s_bricks", "%s_brick_%s"), MOSSY_BRICKS("mossy_%s_bricks", "mossy_%s_brick_%s"), CRACKED_BRICKS("cracked_%s_bricks", "cracked_%s_brick_%s"), POLISHED("polished_%s"), CHISELED("chiseled_%s"), TILES("%s_tiles", "%s_tile_%s"), CRACKED_TILES("cracked_%s_tiles", "cracked_%s_tile_%s"), PILLAR("%s_pillar"), SHINGLES("%s_shingles", "%s_shingle_%s"), PAVERS("%s_pavers", "%s_paver_%s"), PLATES("%s_plates", "%s_plate_%s");

        private final String template;
        private final String additionalTemplate;

        BlockVariant(String template) {
            this(template, template + "_%s");
        }

        BlockVariant(String template, String additionalTemplate) {
            this.template = template;
            this.additionalTemplate = additionalTemplate;
        }

        public String getName(StoneType stoneType) {
            return this.template.formatted(stoneType.getName());
        }

        public String getAdditionalName(StoneType stoneType, String additional) {
            return this.additionalTemplate.formatted(stoneType.getName(), additional);
        }

        public boolean supportsAdditionalBlocks() {
            return this == REGULAR || this == COBBLED || this == MOSSY_COBBLED || this == BRICKS || this == MOSSY_BRICKS || this == POLISHED || this == TILES || this == SHINGLES || this == PAVERS || this == PLATES;
        }
    }
}
