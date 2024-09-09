package fuzs.stoneworks.data;

import fuzs.puzzleslib.api.data.v2.AbstractRecipeProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import fuzs.stoneworks.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.world.block.variant.StoneVariantsProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Block;

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
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.stairs(), variant.block());
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.slab(), variant.block(), 2);
                stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.wall(), variant.block());
                if (variant.block() != baseBlock) {
                    stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.stairs(), baseBlock);
                    stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.slab(), baseBlock, 2);
                    stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, variant.wall(), baseBlock);
                }
            }
        }
    }
}
