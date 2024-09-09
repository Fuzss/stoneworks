package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addRecipes(RecipeOutput recipeOutput) {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            Block baseBlock = variant.stoneType().getBaseBlock(variant.blockVariant());
            stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.block(), baseBlock);
            if (variant.blockVariant().supportsAdditionalBlocks()) {
                Ingredient ingredient = baseBlock != variant.block() ? Ingredient.of(baseBlock, variant.block()) : Ingredient.of(baseBlock);
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.stairs(), ingredient);
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.slab(), ingredient, 2);
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.wall(), ingredient);
            }
        }
    }

    public static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, Ingredient material) {
        stonecutterResultFromBase(recipeOutput, category, result, material, 1);
    }

    public static void stonecutterResultFromBase(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, Ingredient material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(material, category, result, resultCount)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, getConversionRecipeName(result, material) + "_stonecutting");
    }

    public static String getHasName(Ingredient ingredient) {
        return "has_" + getItemName(ingredient);
    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(Ingredient ingredient) {
        return inventoryTrigger(ItemPredicate.Builder.item()
                .of(Arrays.stream(ingredient.getItems()).map(ItemStack::getItem).toArray(ItemLike[]::new))
                .build());
    }

    public static String getConversionRecipeName(ItemLike result, Ingredient ingredient) {
        return getItemName(result) + "_from_" + getItemName(ingredient);
    }

    public static String getItemName(Ingredient ingredient) {
        return Arrays.stream(ingredient.getItems()).map(ItemStack::getItem)
                .map(RecipeProvider::getItemName)
                .collect(Collectors.joining("_or_"));
    }
}
