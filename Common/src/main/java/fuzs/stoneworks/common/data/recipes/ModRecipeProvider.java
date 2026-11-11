package fuzs.stoneworks.common.data.recipes;

import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetFamily;
import fuzs.puzzleslib.common.api.init.v3.family.BlockSetVariant;
import fuzs.stoneworks.common.init.ModBlockFamilies;
import fuzs.stoneworks.common.init.ModBlockVariants;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        for (BlockSetFamily family : ModBlockFamilies.getBaseFamilies()) {
            this.generateFor(family);
            for (BlockSetVariant variant : ModBlockVariants.STONECUTTER_VARIANTS) {
                Holder.Reference<Block> holder = family.getGeneratedBlockVariants().get(variant);
                if (holder == null) {
                    continue;
                }

                Block resultBlock = holder.value();
                Block inputBlock = family.getBaseBlock().value();
                this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, resultBlock, inputBlock);
                if (ModBlockVariants.CRAFTING_VARIANTS.contains(variant)) {
                    this.twoByTwo(resultBlock, inputBlock);
                }
            }

            ModBlockFamilies.getChildFamilies(family).forEach(this::generateFor);
        }

        for (BlockSetFamily family : ModBlockFamilies.getBaseFamilies()) {
            ModBlockVariants.SMELTING_VARIANTS.forEach((BlockSetVariant result, BlockSetVariant base) -> {
                this.generateSmeltingRecipe(family, result, base);
            });
        }
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
     * @see net.minecraft.data.recipes.RecipeProvider#generateSmeltingRecipe(BlockFamily.Variant, Block, ItemLike)
     */
    public final void generateSmeltingRecipe(BlockSetFamily family, BlockSetVariant result, BlockSetVariant base) {
        Holder<Block> resultBlock = family.getGeneratedBlockVariants().get(result);
        Holder<Block> baseBlock = family.getBlock(base);
        if (resultBlock != null && baseBlock != null) {
            this.smeltingResultFromBase(resultBlock.value(), baseBlock.value());
        }
    }
}
