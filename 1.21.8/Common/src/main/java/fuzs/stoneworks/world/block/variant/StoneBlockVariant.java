package fuzs.stoneworks.world.block.variant;

import fuzs.puzzleslib.api.core.v1.utility.ResourceLocationHelper;
import fuzs.stoneworks.Stoneworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class StoneBlockVariant {
    private final StoneType stoneType;
    private final BlockVariant blockVariant;
    private final Block[] blocks;

    public StoneBlockVariant(StoneType stoneType, BlockVariant blockVariant) {
        this(stoneType, blockVariant, new Block[4]);
    }

    protected StoneBlockVariant(StoneType stoneType, BlockVariant blockVariant, Block[] blocks) {
        this.stoneType = stoneType;
        this.blockVariant = blockVariant;
        this.blocks = blocks;
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
        return Objects.requireNonNull(this.block(0, this.blockName()), "base block is null");
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
            if (!BuiltInRegistries.BLOCK.containsKey(id)) {
                if (this.isVanillaVariant()) {
                    // stupid hack so this does not run every time when the vanilla variant simply doesn't have the block type
                    this.blocks[index] = Blocks.AIR;
                } else {
                    throw new IllegalArgumentException("Not a valid block: " + id);
                }
            } else {
                this.blocks[index] = BuiltInRegistries.BLOCK.getValue(id);
            }
        }
        return this.blocks[index] == Blocks.AIR ? null : this.blocks[index];
    }

    public ResourceLocation id(String key) {
        return ResourceLocationHelper.fromNamespaceAndPath(Stoneworks.MOD_ID, key);
    }

    public BlockState baseBlockState() {
        return this.stoneType.getDefaultBlockState(this.blockVariant);
    }

    public BlockBehaviour.Properties baseBlockProperties() {
        return this.stoneType.getBlockProperties(this.blockVariant);
    }

    public boolean isVanillaVariant() {
        return false;
    }
}
