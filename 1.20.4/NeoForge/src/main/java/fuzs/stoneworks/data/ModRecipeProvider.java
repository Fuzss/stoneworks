package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v1.AbstractRecipeProvider;
import fuzs.stoneworks.Stoneworks;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(GatherDataEvent evt, String modId) {
        super(evt, modId);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
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
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(pMaterial), RecipeCategory.BUILDING_BLOCKS, pResult, pResultCount).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, Stoneworks.id(recipeId));
    }
}
