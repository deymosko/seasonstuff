package com.grape.grapes_ss.core.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import com.grape.grapes_ss.core.compat.rei.category.PalmBarCategory;
import com.grape.grapes_ss.core.compat.rei.display.PalmBarDisplay;
import com.grape.grapes_ss.core.recipe.PalmBarRecipe;
import com.grape.grapes_ss.core.registry.ObjectRegistry;

public class BeachpartyREIClientPlugin {
    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new PalmBarCategory());
        registry.addWorkstations(PalmBarCategory.PALM_BAR_DISPLAY, EntryStacks.of(ObjectRegistry.PALM_BAR.get()));
    }

    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(PalmBarRecipe.class, PalmBarDisplay::new);
    }
}
