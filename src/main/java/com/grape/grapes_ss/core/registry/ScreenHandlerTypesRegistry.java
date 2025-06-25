package net.satisfy.beachparty.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.client.gui.handler.MiniFridgeGuiHandler;
import net.satisfy.beachparty.client.gui.handler.PalmBarGuiHandler;

import java.util.function.Supplier;


public class ScreenHandlerTypesRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Beachparty.MOD_ID);

    public static void init() {
        MENU_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<MenuType<PalmBarGuiHandler>> PALM_BAR_GUI_HANDLER = create("palm_bar_gui_handler", () -> new MenuType<>(PalmBarGuiHandler::new, FeatureFlags.VANILLA_SET));

    private static <T extends MenuType<?>> RegistryObject<T> create(String name, Supplier<T> type) {
        return MENU_TYPES.register(name, type);
    }

    public static final RegistryObject<MenuType<MiniFridgeGuiHandler>> MINI_FRIDGE_GUI_HANDLER = create("mini_fridge_gui_handler", () -> new MenuType<>(MiniFridgeGuiHandler::new, FeatureFlags.VANILLA_SET));


}
