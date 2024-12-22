package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            Block baseBlock = variant.stoneType().getBaseBlock(variant.blockVariant());
            this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, variant.block(), baseBlock);
            if (variant.blockVariant().supportsAdditionalBlocks()) {
                Ingredient ingredient = baseBlock != variant.block() ? Ingredient.of(baseBlock, variant.block()) :
                        Ingredient.of(variant.block());
                this.stair(RecipeCategory.BUILDING_BLOCKS, variant.stairs(), variant.block());
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, variant.stairs(), ingredient);
                this.slab(RecipeCategory.BUILDING_BLOCKS, variant.slab(), variant.block());
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, variant.slab(), ingredient, 2);
                this.wall(RecipeCategory.BUILDING_BLOCKS, variant.wall(), variant.block());
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, variant.wall(), ingredient);
            }
        }
    }

    @Deprecated(forRemoval = true)
    public void stair(RecipeCategory recipeCategory, ItemLike result, ItemLike material) {
        this.stairBuilder(recipeCategory, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), this.has(material))
                .save(this.output);
    }

    @Deprecated(forRemoval = true)
    public RecipeBuilder stairBuilder(RecipeCategory recipeCategory, ItemLike result, Ingredient material) {
        return this.shaped(recipeCategory, result, 4)
                .define('#', material)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###");
    }

    @Deprecated(forRemoval = true)
    public void stonecutterResultFromBase(RecipeCategory category, ItemLike result, Ingredient material) {
        this.stonecutterResultFromBase(this.output, category, result, material);
    }

    @Deprecated(forRemoval = true)
    public void stonecutterResultFromBase(RecipeCategory category, ItemLike result, Ingredient material, int resultCount) {
        this.stonecutterResultFromBase(this.output, category, result, material, resultCount);
    }
}
