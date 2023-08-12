package fuzs.stoneworks.world.block.variant;

public enum BlockVariant {
    REGULAR("raw_%s"), COBBLED("cobbled_%s"), MOSSY_COBBLED("mossy_cobbled_%s"), BRICKS("%s_bricks", "%s_brick_%s"), MOSSY_BRICKS("mossy_%s_bricks", "mossy_%s_brick_%s"), CRACKED_BRICKS("cracked_%s_bricks", "cracked_%s_brick_%s"), POLISHED("polished_%s"), CHISELED("chiseled_%s"), TILES("%s_tiles", "%s_tile_%s"), CRACKED_TILES("cracked_%s_tiles", "cracked_%s_tile_%s"), PILLAR("%s_pillar"), SHINGLES("%s_shingles", "%s_shingle_%s"), PAVERS("%s_pavers", "%s_paver_%s"), PLATES("%s_plates", "%s_plate_%s");

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
        return this.template.formatted(stoneType.getName(this));
    }

    public String getAdditionalName(StoneType stoneType, String additional) {
        return this.additionalTemplate.formatted(stoneType.getName(this), additional);
    }

    public boolean supportsAdditionalBlocks() {
        return this == REGULAR || this == COBBLED || this == MOSSY_COBBLED || this == BRICKS || this == MOSSY_BRICKS || this == POLISHED || this == TILES || this == SHINGLES || this == PAVERS || this == PLATES;
    }
    
    public boolean usesNetherbricksMaterial() {
        return this == BlockVariant.CHISELED || this == BlockVariant.TILES || this == BlockVariant.CRACKED_TILES || this == BlockVariant.PILLAR || this == BlockVariant.SHINGLES || this == BlockVariant.PAVERS || this == BlockVariant.PLATES;
    }
}
