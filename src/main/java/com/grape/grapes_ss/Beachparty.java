package com.grape.grapes_ss;



public class Beachparty {
    public static final String MOD_ID = "grapes_ss";

    public static void init() {
        ObjectRegistry.init();
        EntityTypeRegistry.init();
        TabRegistry.init();
        PlacerTypesRegistry.init();
        MobEffectRegistry.init();
        SoundEventRegistry.init();
        ScreenHandlerTypesRegistry.init();
        CommonEvents.init();
        RecipeRegistry.init();
    }

    public static void commonSetup() {
    }
}