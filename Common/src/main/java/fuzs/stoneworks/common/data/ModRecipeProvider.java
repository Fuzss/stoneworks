package fuzs.stoneworks.common.data;

import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import fuzs.stoneworks.common.world.block.variant.StoneBlockVariant;
import fuzs.stoneworks.common.world.block.variant.StoneVariantsProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        for (StoneBlockVariant variant : StoneVariantsProvider.getStoneBlockVariants().toList()) {
            Block baseBlock = variant.stoneType().getBaseBlock(variant.blockVariant());
            this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, variant.block(), baseBlock);
            if (variant.blockVariant().supportsAdditionalBlocks()) {
                Ingredient ingredient = baseBlock != variant.block() ? Ingredient.of(baseBlock, variant.block()) :
                        Ingredient.of(variant.block());
                this.stair(RecipeCategory.BUILDING_BLOCKS, variant.stairs(), variant.block());
                this.stonecutterResultFromBaseBuilder(RecipeCategory.BUILDING_BLOCKS, variant.stairs(), ingredient)
                        .unlockedBy(getHasName(variant.block()), this.has(variant.block()))
                        .save(this.output, getStonecuttingRecipeName(variant.stairs(), variant.block()));
                this.slab(RecipeCategory.BUILDING_BLOCKS, variant.slab(), variant.block());
                this.stonecutterResultFromBaseBuilder(RecipeCategory.BUILDING_BLOCKS, variant.slab(), ingredient, 2)
                        .unlockedBy(getHasName(variant.block()), this.has(variant.block()))
                        .save(this.output, getStonecuttingRecipeName(variant.slab(), variant.block()));
                this.wall(RecipeCategory.BUILDING_BLOCKS, variant.wall(), variant.block());
                this.stonecutterResultFromBaseBuilder(RecipeCategory.BUILDING_BLOCKS, variant.wall(), ingredient)
                        .unlockedBy(getHasName(variant.block()), this.has(variant.block()))
                        .save(this.output, getStonecuttingRecipeName(variant.wall(), variant.block()));
            }
        }
    }
}
