package com.grape.grapes_ss.forge.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.forge.REIPluginClient;
import com.grape.grapes_ss.core.compat.rei.BeachpartyREIClientPlugin;

@REIPluginClient
public class BeachpartyREIClientPluginForge implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        BeachpartyREIClientPlugin.registerCategories(registry);
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        BeachpartyREIClientPlugin.registerDisplays(registry);
    }
}
