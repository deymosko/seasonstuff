package net.satisfy.beachparty.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.satisfy.beachparty.Beachparty;

@SuppressWarnings("unused")
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> BEACHPARTY_TABS = DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, Beachparty.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BEACHPARTY_TAB = BEACHPARTY_TABS.register("beachparty", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(() -> new ItemStack(ObjectRegistry.COCONUT_COCKTAIL.get()))
            .title(Component.translatable("creativetab.beachparty.tab").withStyle(style -> style.withColor(TextColor.fromRgb(0xc0924a))))
            .displayItems((parameters, out) -> {
                out.accept(ObjectRegistry.PALM_BAR.get());
                out.accept(ObjectRegistry.PALM_BAR_STOOL.get());
                out.accept(ObjectRegistry.BEACH_CHAIR.get());
                out.accept(ObjectRegistry.PALM_TABLE.get());
                out.accept(ObjectRegistry.THATCH_STAIRS.get());
                out.accept(ObjectRegistry.SANDWAVES.get());
                out.accept(ObjectRegistry.PALM_TORCH_ITEM.get());
                out.accept(ObjectRegistry.TALL_PALM_TORCH.get());
                out.accept(ObjectRegistry.PALM_SPROUT.get());
                // removed items
                out.accept(ObjectRegistry.MUSIC_DISC_BEACHPARTY.get());
                out.accept(ObjectRegistry.MUSIC_DISC_CARIBBEAN_BEACH.get());
                out.accept(ObjectRegistry.MUSIC_DISC_PRIDELANDS.get());
                out.accept(ObjectRegistry.MUSIC_DISC_VOCALISTA.get());
                out.accept(ObjectRegistry.MUSIC_DISC_WILD_VEINS.get());
                out.accept(ObjectRegistry.MESSAGE_IN_A_BOTTLE_ITEM.get());
                out.accept(ObjectRegistry.SEASHELL.get());
                out.accept(ObjectRegistry.COCONUT_COCKTAIL.get());
                out.accept(ObjectRegistry.SWEETBERRIES_COCKTAIL.get());
                out.accept(ObjectRegistry.COCOA_COCKTAIL.get());
                out.accept(ObjectRegistry.PUMPKIN_COCKTAIL.get());
                out.accept(ObjectRegistry.MELON_COCKTAIL.get());
                out.accept(ObjectRegistry.HONEY_COCKTAIL.get());
                out.accept(ObjectRegistry.RAW_MUSSEL_MEAT.get());
                out.accept(ObjectRegistry.COOKED_MUSSEL_MEAT.get());
                out.accept(ObjectRegistry.CROCS.get());
                out.accept(ObjectRegistry.SWIM_WINGS.get());
                out.accept(ObjectRegistry.RUBBER_RING_BLUE.get());
                out.accept(ObjectRegistry.RUBBER_RING_PINK.get());
                out.accept(ObjectRegistry.RUBBER_RING_STRIPPED.get());
                out.accept(ObjectRegistry.RUBBER_RING_AXOLOTL.get());
                out.accept(ObjectRegistry.RUBBER_RING_PELICAN.get());
                out.accept(ObjectRegistry.POOL_NOODLE.get());
                out.accept(ObjectRegistry.BEACHPARTY_BANNER.get());

            })
            .build());

    public static void init() {
        BEACHPARTY_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
