package fuzs.stoneworks.data;

import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            Block baseBlock = variant.stoneType().getBaseBlock(variant.blockVariant());
            stonecutterResultFromBase(recipeConsumer, variant.block(), baseBlock);
            if (variant.blockVariant().supportsAdditionalBlocks()) {
                stonecutterResultFromBase(recipeConsumer, variant.stairs(), variant.block());
                stonecutterResultFromBase(recipeConsumer, variant.slab(), variant.block(), 2);
                stonecutterResultFromBase(recipeConsumer, variant.wall(), variant.block());
                if (variant.block() != baseBlock) {
                    stonecutterResultFromBase(recipeConsumer, variant.stairs(), baseBlock);
                    stonecutterResultFromBase(recipeConsumer, variant.slab(), baseBlock, 2);
                    stonecutterResultFromBase(recipeConsumer, variant.wall(), baseBlock);
                }
            }
        }
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial) {
        stonecutterResultFromBase(pFinishedRecipeConsumer, pResult, pMaterial, 1);
    }

    protected static void stonecutterResultFromBase(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pMaterial, int pResultCount) {
        String recipeId = getConversionRecipeName(pResult, pMaterial) + "_stonecutting";
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, Stoneworks.id(recipeId));
    }
}
