package com.grape.grapes_ss.forge.client;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.client.BeachPartyClient;
import com.grape.grapes_ss.client.model.FloatyBoatModel;
import com.grape.grapes_ss.core.entity.PalmBoatEntity;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import com.grape.grapes_ss.forge.client.integration.*;
import com.grape.grapes_ss.forge.client.renderer.player.layers.*;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = Beachparty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BeachpartyClientForge {

    @SubscribeEvent
    public static void beforeClientSetup(RegisterEvent event) {
        BeachPartyClient.preInitClient();
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BeachPartyClient.initClient();
        });
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (PalmBoatEntity.Type type : PalmBoatEntity.Type.values()) {
            if (type == PalmBoatEntity.Type.FLOATY) {
                event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Beachparty.MOD_ID, type.getModelLocation()), "main"), FloatyBoatModel::createBodyModel);
                event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Beachparty.MOD_ID, type.getChestModelLocation()), "main"), FloatyBoatModel::createBodyModel);
            } else {
                event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Beachparty.MOD_ID, type.getModelLocation()), "main"), BoatModel::createBodyModel);
                event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(Beachparty.MOD_ID, type.getChestModelLocation()), "main"), ChestBoatModel::createBodyModel);
            }
        }
    }

    private static <E extends Player, M extends HumanoidModel<E>>
    void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName, Function<LivingEntityRenderer<E, M>, ? extends RenderLayer<E, M>> factory) {
        LivingEntityRenderer<E, M> renderer = event.getSkin(skinName);
        if (renderer != null) renderer.addLayer(factory.apply(renderer));
    }
}
