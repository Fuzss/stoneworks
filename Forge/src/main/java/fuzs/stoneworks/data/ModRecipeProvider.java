package fuzs.stoneworks.data;

import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            stonecutterResultFromBase(recipeConsumer, variant.block(), variant.stoneType().getBaseBlock(variant.blockVariant()));
            if (variant.blockVariant().supportsAdditionalBlocks()) {
                stonecutterResultFromBase(recipeConsumer, variant.stairs(), variant.block());
                stonecutterResultFromBase(recipeConsumer, variant.slab(), variant.block(), 2);
                stonecutterResultFromBase(recipeConsumer, variant.wall(), variant.block());
            }
        }
    }
}
