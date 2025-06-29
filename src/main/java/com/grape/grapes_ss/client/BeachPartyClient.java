package com.grape.grapes_ss.client;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import com.grape.grapes_ss.client.gui.MiniFridgeGui;
import com.grape.grapes_ss.client.gui.PalmBarGui;
import com.grape.grapes_ss.client.model.*;
import com.grape.grapes_ss.client.renderer.entity.ChairRenderer;
import com.grape.grapes_ss.client.renderer.entity.PalmBoatRenderer;
import com.grape.grapes_ss.core.registry.EntityTypeRegistry;
import com.grape.grapes_ss.core.registry.ScreenHandlerTypesRegistry;
import com.grape.grapes_ss.core.util.BeachpartyUtil;

import static com.grape.grapes_ss.core.registry.ObjectRegistry.*;

public class BeachPartyClient {
    public static void initClient() {
        RenderTypeRegistry.register(RenderType.cutout(), PALM_TABLE.get(), PALM_TORCH.get(), PALM_WALL_TORCH.get(), TALL_PALM_TORCH.get(), THATCH_STAIRS.get(), MELON_COCKTAIL.get(), COCONUT_COCKTAIL.get(), HONEY_COCKTAIL.get(), SWEETBERRIES_COCKTAIL.get(), PUMPKIN_COCKTAIL.get(), COCOA_COCKTAIL.get(), SANDCASTLE.get(), MESSAGE_IN_A_BOTTLE.get(), PALM_SPROUT.get(), SEASHELL_BLOCK.get(), SAND_BUCKET_BLOCK_EMPTY.get(), PALM_BAR_STOOL.get());

        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.PALM_BAR_GUI_HANDLER.get(), PalmBarGui::new);
        MenuRegistry.registerScreenFactory(ScreenHandlerTypesRegistry.MINI_FRIDGE_GUI_HANDLER.get(), MiniFridgeGui::new);

        // sign renderers removed
    }

    public static void preInitClient() {
        registerEntityRenderers();
        registerEntityModelLayers();
    }

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(EntityTypeRegistry.CHAIR, ChairRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.COCONUT, ThrownItemRenderer::new);
        EntityRendererRegistry.register(EntityTypeRegistry.PALM_BOAT, context -> new PalmBoatRenderer<>(context, false));
        EntityRendererRegistry.register(EntityTypeRegistry.PALM_CHEST_BOAT, context -> new PalmBoatRenderer<>(context, true));
    }

    public static void registerEntityModelLayers() {
        EntityModelLayerRegistry.register(BeachHatModel.LAYER_LOCATION, BeachHatModel::createBodyLayer);
        EntityModelLayerRegistry.register(SunglassesModel.LAYER_LOCATION, SunglassesModel::createBodyLayer);
        EntityModelLayerRegistry.register(BikiniModel.LAYER_LOCATION, BikiniModel::createBodyLayer);
        EntityModelLayerRegistry.register(TrunksModel.LAYER_LOCATION, TrunksModel::createBodyLayer);
        EntityModelLayerRegistry.register(BeachBallModel.LAYER_LOCATION, BeachBallModel::createBodyLayer);
        EntityModelLayerRegistry.register(FloatyBoatModel.LAYER_LOCATION, BeachHatModel::createBodyLayer);
    }
}