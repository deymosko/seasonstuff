package com.grape.grapes_ss.core.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import com.grape.grapes_ss.client.gui.handler.PalmBarGuiHandler;
import com.grape.grapes_ss.core.compat.jei.categorys.PalmBarCategory;
import com.grape.grapes_ss.core.recipe.PalmBarRecipe;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import com.grape.grapes_ss.core.registry.RecipeRegistry;
import com.grape.grapes_ss.core.registry.ScreenHandlerTypesRegistry;
import com.grape.grapes_ss.core.util.BeachpartyIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class BeachpartyJEIPlugin implements IModPlugin {
    public static void addSlot(IRecipeLayoutBuilder builder, int x, int y, Ingredient ingredient) {
        builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredients(ingredient);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PalmBarCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<PalmBarRecipe> barRecipes = rm.getAllRecipesFor(RecipeRegistry.PALM_BAR_RECIPE_TYPE.get());
        registration.addRecipes(PalmBarCategory.PALM_BAR, barRecipes);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new BeachpartyIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ObjectRegistry.PALM_BAR.get().asItem().getDefaultInstance(), PalmBarCategory.PALM_BAR);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(PalmBarGuiHandler.class, ScreenHandlerTypesRegistry.PALM_BAR_GUI_HANDLER.get(), PalmBarCategory.PALM_BAR, 1, 4, 2, 36);

    }
}
