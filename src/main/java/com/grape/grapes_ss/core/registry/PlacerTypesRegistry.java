package net.satisfy.beachparty.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.satisfy.beachparty.Beachparty;

public class PlacerTypesRegistry {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Beachparty.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.TRUNK_PLACER_TYPES, Beachparty.MOD_ID);


    public static void init() {
        FOLIAGE_PLACER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        TRUNK_PLACER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
