package fuzs.stoneworks.common.data.recipes;

import com.google.common.collect.ImmutableMap;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModBlockVariants;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.Optional;

public class ModRecipeProvider extends AbstractRecipeProvider {
    private static final FamilyRecipeProvider TWO_BY_TWO = (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
        ((ModRecipeProvider) recipeProvider).twoByTwo(result, input);
    };
    private static final Map<BlockSetVariant, FamilyRecipeProvider> CRAFTING_VARIANTS = ImmutableMap.of(ModBlockVariants.SHINGLES,
            TWO_BY_TWO,
            ModBlockVariants.PAVERS,
            TWO_BY_TWO,
            ModBlockVariants.PLATES,
            TWO_BY_TWO);
    private static final Map<BlockSetVariant, FamilyRecipeProvider> STONECUTTING_VARIANTS = ImmutableMap.<BlockSetVariant, FamilyRecipeProvider>builder()
            .put(ModBlockVariants.REGULAR, FamilyRecipeProvider.stonecutting())
            .put(ModBlockVariants.CHISELED, FamilyRecipeProvider.stonecutting())
            .put(ModBlockVariants.SHINGLES, FamilyRecipeProvider.stonecutting())
            .put(ModBlockVariants.PAVERS, FamilyRecipeProvider.stonecutting())
            .put(ModBlockVariants.PLATES, FamilyRecipeProvider.stonecutting())
            .build();
    private static final Map<BlockSetVariant, BlockSetVariant> MOSSY_VARIANTS = Map.of(ModBlockVariants.MOSSY_COBBLED,
            ModBlockVariants.COBBLED,
            ModBlockVariants.MOSSY_BRICKS,
            ModBlockVariants.BRICKS);
    private static final Map<BlockSetVariant, BlockSetVariant> SMELTING_VARIANTS = Map.of(ModBlockVariants.CRACKED_BRICKS,
            ModBlockVariants.BRICKS,
            ModBlockVariants.CRACKED_TILES,
            ModBlockVariants.TILES);

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    public static Map<BlockSetVariant, FamilyRecipeProvider> getCraftingVariants(BlockSetFamily family) {
        ImmutableMap.Builder<BlockSetVariant, FamilyRecipeProvider> builder = ImmutableMap.builder();
        builder.putAll(CRAFTING_VARIANTS);
        MOSSY_VARIANTS.forEach((BlockSetVariant variant, BlockSetVariant baseVariant) -> {
            builder.put(variant,
                    (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                        ((ModRecipeProvider) recipeProvider).mossy(result, family.getBlock(baseVariant).value());
                    });
        });

        BlockSetFamily regularFamily = ModBlockFamilies.getChildFamily(family, ModBlockVariants.REGULAR);
        if (regularFamily != null) {
            builder.put(ModBlockVariants.CHISELED,
                    (RecipeProvider recipeProvider, ItemLike result, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                        recipeProvider.chiseled(RecipeCategory.BUILDING_BLOCKS,
                                result,
                                regularFamily.getBlock(BlockSetVariant.SLAB).value());
                    });
        }

        return builder.build();
    }

    public static Map<BlockSetVariant, FamilyRecipeProvider> getSmeltingVariants(BlockSetFamily family) {
        ImmutableMap.Builder<BlockSetVariant, FamilyRecipeProvider> builder = ImmutableMap.builder();
        SMELTING_VARIANTS.forEach((BlockSetVariant result, BlockSetVariant base) -> {
            builder.put(result,
                    (RecipeProvider recipeProvider, ItemLike item, ItemLike input, Optional<String> recipeGroupPrefix, Optional<String> recipeUnlockedBy) -> {
                        Holder<Block> baseBlock = family.getBlock(base);
                        if (baseBlock != null) {
                            recipeProvider.smeltingResultFromBase(item, baseBlock.value());
                        }
                    });
        });
        return builder.build();
    }

    @Override
    public void buildRecipes() {
        for (BlockSetFamily family : ModBlockFamilies.getBaseFamilies()) {
            this.generateFor(family, getCraftingVariants(family), STONECUTTING_VARIANTS);
            this.generateSmeltingFor(family, getSmeltingVariants(family));
            ModBlockFamilies.getChildFamilies(family).forEach(this::generateFor);
        }
    }

    // TODO replace with method from puzzles lib
    @Deprecated
    private void generateSmeltingFor(BlockSetFamily family, Map<BlockSetVariant, FamilyRecipeProvider> smeltingVariants) {
        BlockFamily blockFamily = family.getBlockFamily();
        family.getGeneratedItemVariants().forEach((BlockSetVariant variant, Holder.Reference<Item> holder) -> {
            FamilyRecipeProvider recipeProvider = smeltingVariants.get(variant);
            if (recipeProvider != null) {
                recipeProvider.create(this,
                        holder.value(),
                        family.getBaseBlock().value(),
                        blockFamily.getRecipeGroupPrefix(),
                        blockFamily.getRecipeUnlockedBy());
            }
        });
    }

    /**
     * @see net.minecraft.data.recipes.RecipeProvider#twoByTwoPacker(RecipeCategory, ItemLike, ItemLike)
     */
    public final void twoByTwo(ItemLike result, ItemLike input) {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .define('#', input)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }

    /**
     * @see net.minecraft.data.recipes.packs.VanillaRecipeProvider#buildRecipes()
     */
    public final void mossy(ItemLike result, ItemLike base) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(base)
                .requires(Blocks.VINE)
                .group(getItemName(result))
                .unlockedBy(getHasName(Blocks.VINE), this.has(Blocks.VINE))
                .save(this.output, getConversionRecipeName(result, Blocks.VINE));
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result)
                .requires(base)
                .requires(Blocks.MOSS_BLOCK)
                .group(getItemName(result))
                .unlockedBy(getHasName(Blocks.MOSS_BLOCK), this.has(Blocks.MOSS_BLOCK))
                .save(this.output, getConversionRecipeName(result, Blocks.MOSS_BLOCK));
    }
}
