package org.cyclops.cyclopscore.recipe.type;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

/**
 * @author rubensworks
 */
public class RecipeCraftingShapedCustomOutput extends ShapedRecipe {

    private final RecipeSerializerCraftingShapedCustomOutput serializer;
    private final ItemStack recipeOutput;

    public RecipeCraftingShapedCustomOutput(RecipeSerializerCraftingShapedCustomOutput serializer, ResourceLocation idIn, String groupIn, CraftingBookCategory category, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
        super(idIn, groupIn, category, recipeWidthIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
        this.serializer = serializer;
        this.recipeOutput = recipeOutputIn;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public ItemStack assemble(CraftingContainer inv, RegistryAccess registryAccess) {
        RecipeSerializerCraftingShapedCustomOutput.IOutputTransformer outputTransformer = serializer.getOutputTransformer();
        if (outputTransformer != null) {
            return outputTransformer.transform(inv, this.getResultItem());
        }
        return this.getResultItem().copy();
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return getResultItem();
    }

    public ItemStack getResultItem() {
        return this.recipeOutput;
    }
}