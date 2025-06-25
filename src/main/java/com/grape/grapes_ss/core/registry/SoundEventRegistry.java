package net.satisfy.beachparty.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.core.util.BeachpartyIdentifier;

import java.util.List;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Beachparty.MOD_ID);

    public static final RegistryObject<SoundEvent> OVER_THE_RAINBOW = create("over_the_rainbow");
    public static final RegistryObject<SoundEvent> BEACHPARTY = create("beachparty");
    public static final RegistryObject<SoundEvent> CARIBBEAN_BEACH = create("caribbean_beach");
    public static final RegistryObject<SoundEvent> PRIDELANDS = create("pridelands");
    public static final RegistryObject<SoundEvent> VOCALISTA = create("vocalista");
    public static final RegistryObject<SoundEvent> WILD_VEINS = create("wild_veins");

    public static final List<RegistryObject<SoundEvent>> RADIO_SOUNDS = List.of(BEACHPARTY, CARIBBEAN_BEACH, PRIDELANDS, VOCALISTA, WILD_VEINS, OVER_THE_RAINBOW);

    private static RegistryObject<SoundEvent> create(String name) {
        final ResourceLocation id = new BeachpartyIdentifier(name);
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void init() {
        SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
