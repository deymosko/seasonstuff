package com.grape.grapes_ss.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.effect.MobEffect;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.effect.OceanWalkEffect;
import com.grape.grapes_ss.core.util.BeachpartyIdentifier;

import java.util.function.Supplier;

public class MobEffectRegistry {
    private static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Beachparty.MOD_ID);

    public static final RegistryObject<MobEffect> OCEAN_WALK;

    private static RegistryObject<MobEffect> registerEffect(String name, Supplier<MobEffect> effect) {
        return MOB_EFFECTS.register(name, effect);
    }

    public static void init() {
        MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    static {
        OCEAN_WALK = registerEffect("ocean_walk", OceanWalkEffect::new);
    }
}
