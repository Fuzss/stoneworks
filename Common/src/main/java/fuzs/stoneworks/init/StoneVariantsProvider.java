package fuzs.stoneworks.init;

import com.google.common.collect.Maps;
import fuzs.stoneworks.Stoneworks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Locale;
import java.util.Map;

public class StoneVariantsProvider {
    private static final Map<String, StoneBlockVariant> STONE_BLOCK_VARIANTS = Maps.newHashMap();

    static {
        registerAllStoneBlockVariants();
        registerVanillaOverrides();
    }

    private static void registerAllStoneBlockVariants() {
        for (StoneType stoneType : StoneType.values()) {
            for (BlockVariant blockVariant : BlockVariant.values()) {
                STONE_BLOCK_VARIANTS.put(blockVariant.getName(stoneType), new StoneBlockVariant(stoneType, blockVariant));
            }
        }
    }

    private static void registerVanillaOverrides() {
        registerVanillaOverride("stone", StoneType.STONE, BlockVariant.REGULAR);
        registerVanillaOverride("cobblestone", StoneType.STONE, BlockVariant.COBBLED);
        registerVanillaOverride("mossy_cobblestone", StoneType.STONE, BlockVariant.MOSSY_COBBLED);
        registerVanillaOverride("stone_bricks", StoneType.STONE, BlockVariant.BRICKS);
        registerVanillaOverride("mossy_stone_bricks", StoneType.STONE, BlockVariant.MOSSY_BRICKS);
        registerVanillaOverride("cracked_stone_bricks", StoneType.STONE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride("smooth_stone", StoneType.STONE, BlockVariant.POLISHED);
        registerVanillaOverride("chiseled_stone_bricks", StoneType.STONE, BlockVariant.CHISELED);
        registerVanillaOverride("andesite", StoneType.ANDESITE, BlockVariant.REGULAR);
        registerVanillaOverride("polished_andesite", StoneType.ANDESITE, BlockVariant.POLISHED);
        registerVanillaOverride("granite", StoneType.GRANITE, BlockVariant.REGULAR);
        registerVanillaOverride("polished_granite", StoneType.GRANITE, BlockVariant.POLISHED);
        registerVanillaOverride("diorite", StoneType.DIORITE, BlockVariant.REGULAR);
        registerVanillaOverride("polished_diorite", StoneType.DIORITE, BlockVariant.POLISHED);
        registerVanillaOverride("deepslate", StoneType.DEEPSLATE, BlockVariant.REGULAR);
        registerVanillaOverride("cobbled_deepslate", StoneType.DEEPSLATE, BlockVariant.COBBLED);
        registerVanillaOverride("deepslate_bricks", StoneType.DEEPSLATE, BlockVariant.BRICKS);
        registerVanillaOverride("cracked_deepslate_bricks", StoneType.DEEPSLATE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride("polished_deepslate", StoneType.DEEPSLATE, BlockVariant.POLISHED);
        registerVanillaOverride("chiseled_deepslate", StoneType.DEEPSLATE, BlockVariant.CHISELED);
        registerVanillaOverride("deepslate_tiles", StoneType.DEEPSLATE, BlockVariant.TILES);
        registerVanillaOverride("cracked_deepslate_tiles", StoneType.DEEPSLATE, BlockVariant.CRACKED_TILES);
        registerVanillaOverride("calcite", StoneType.CALCITE, BlockVariant.REGULAR);
        registerVanillaOverride("tuff", StoneType.TUFF, BlockVariant.REGULAR);
        registerVanillaOverride("basalt", StoneType.BASALT, BlockVariant.REGULAR);
        registerVanillaOverride("smooth_basalt", StoneType.BASALT, BlockVariant.POLISHED);
        registerVanillaOverride("polished_basalt", StoneType.BASALT, BlockVariant.PILLAR);
        registerVanillaOverride("blackstone", StoneType.BLACKSTONE, BlockVariant.REGULAR);
        registerVanillaOverride("polished_blackstone_bricks", StoneType.BLACKSTONE, BlockVariant.BRICKS);
        registerVanillaOverride("cracked_polished_blackstone_bricks", StoneType.BLACKSTONE, BlockVariant.CRACKED_BRICKS);
        registerVanillaOverride("polished_blackstone", StoneType.BLACKSTONE, BlockVariant.POLISHED);
        registerVanillaOverride("chiseled_polished_blackstone", StoneType.BLACKSTONE, BlockVariant.CHISELED);
        registerVanillaOverride("netherrack", StoneType.NETHERRACK, BlockVariant.REGULAR);
        registerVanillaOverride("chiseled_nether_bricks", StoneType.NETHERRACK, BlockVariant.CHISELED);
        registerVanillaOverride("nether_bricks", StoneType.NETHERRACK, BlockVariant.TILES);
        registerVanillaOverride("cracked_nether_bricks", StoneType.NETHERRACK, BlockVariant.CRACKED_TILES);
        registerVanillaOverride("end_stone", StoneType.END_STONE, BlockVariant.REGULAR);
        registerVanillaOverride("end_stone_bricks", StoneType.END_STONE, BlockVariant.BRICKS);
        registerVanillaOverride("purpur_block", StoneType.PURPUR, BlockVariant.PLATES);
        registerVanillaOverride("purpur_pillar", StoneType.PURPUR, BlockVariant.PILLAR);
        registerVanillaOverride("prismarine", StoneType.PRISMARINE, BlockVariant.REGULAR);
        registerVanillaOverride("prismarine_bricks", StoneType.PRISMARINE, BlockVariant.SHINGLES);
        registerVanillaOverride("dark_prismarine", StoneType.DARK_PRISMARINE, BlockVariant.PAVERS);
        registerVanillaOverride("sandstone", StoneType.SANDSTONE, BlockVariant.REGULAR);
        registerVanillaOverride("cut_sandstone", StoneType.SANDSTONE, BlockVariant.POLISHED);
        registerVanillaOverride("chiseled_sandstone", StoneType.SANDSTONE, BlockVariant.CHISELED);
        registerVanillaOverride("red_sandstone", StoneType.RED_SANDSTONE, BlockVariant.REGULAR);
        registerVanillaOverride("red_cut_sandstone", StoneType.RED_SANDSTONE, BlockVariant.POLISHED);
        registerVanillaOverride("red_chiseled_sandstone", StoneType.RED_SANDSTONE, BlockVariant.CHISELED);
        registerVanillaOverride("quartz_block", StoneType.QUARTZ, BlockVariant.REGULAR);
        registerVanillaOverride("quartz_bricks", StoneType.QUARTZ, BlockVariant.BRICKS);
        registerVanillaOverride("chiseled_quartz_block", StoneType.QUARTZ, BlockVariant.CHISELED);
        registerVanillaOverride("quartz_pillar", StoneType.QUARTZ, BlockVariant.PILLAR);
    }

    private static void registerVanillaOverride(String vanillaName, StoneType stoneType, BlockVariant blockVariant) {
        if (!Registry.BLOCK.containsKey(new ResourceLocation(vanillaName))) {
            throw new IllegalArgumentException("%s is not a valid block".formatted(new ResourceLocation(vanillaName)));
        }
        if (STONE_BLOCK_VARIANTS.put(blockVariant.getName(stoneType), new VanillaStoneBlockVariant(vanillaName, stoneType, blockVariant)) == null) {
            throw new IllegalStateException("unable to replace stone block variant %s with vanilla equivalent".formatted(blockVariant.getName(stoneType)));
        }
    }

    private static class StoneBlockVariant {
        private final StoneType stoneType;
        private final BlockVariant blockVariant;

        public StoneBlockVariant(StoneType stoneType, BlockVariant blockVariant) {
            this.stoneType = stoneType;
            this.blockVariant = blockVariant;
        }

        public String name() {
            return this.blockVariant.getName(this.stoneType);
        }

        public ResourceLocation id() {
            return Stoneworks.id(this.name());
        }

        public Block block() {
            if (!Registry.BLOCK.containsKey(this.id())) {
                throw new IllegalArgumentException("%s is not a valid block".formatted(this.id()));
            }
            return Registry.BLOCK.get(this.id());
        }
    }

    private static class VanillaStoneBlockVariant extends StoneBlockVariant {
        private final ResourceLocation id;

        public VanillaStoneBlockVariant(String vanillaName, StoneType stoneType, BlockVariant blockVariant) {
            super(stoneType, blockVariant);
            this.id = new ResourceLocation(vanillaName);
        }

        @Override
        public ResourceLocation id() {
            return this.id;
        }
    }

    private enum StoneType {
        STONE, ANDESITE, GRANITE, DIORITE, DEEPSLATE, CALCITE, TUFF, BASALT, BLACKSTONE, NETHERRACK, END_STONE, PURPUR, PRISMARINE, DARK_PRISMARINE, SANDSTONE, RED_SANDSTONE, QUARTZ;

        public String getName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    private enum BlockVariant {
        REGULAR("%s"), COBBLED("cobbled_%s"), MOSSY_COBBLED("mossy_cobbled_%s"), BRICKS("%s_bricks"), MOSSY_BRICKS("mossy_%s_bricks"), CRACKED_BRICKS("cracked_%s_bricks"), POLISHED("polished_%s"), CHISELED("chiseled_%s"), TILES("%s_tiles"), CRACKED_TILES("cracked_%s_tiles"), PILLAR("%s_pillar"), SHINGLES("%s_shingles"), PAVERS("%s_pavers"), PLATES("%s_plates");

        private final String template;

        BlockVariant(String template) {
            this.template = template;
        }

        public String getName(StoneType stoneType) {
            return this.getName(stoneType.getName());
        }

        public String getName(String stoneVariant) {
            return this.template.formatted(stoneVariant);
        }

        public boolean supportsAdditionalBlocks() {
            return this == REGULAR || this == COBBLED || this == MOSSY_COBBLED || this == BRICKS || this == MOSSY_BRICKS || this == POLISHED || this == TILES || this == SHINGLES || this == PAVERS || this == PLATES;
        }
    }
}
