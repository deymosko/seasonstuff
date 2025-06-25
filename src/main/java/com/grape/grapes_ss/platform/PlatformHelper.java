package com.grape.grapes_ss.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.forge.registry.BeachpartyConfig;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Beachparty.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlatformHelper {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Beachparty.MOD_ID);

    public static void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
    }

    public static void onUseSeashell(Level world, Player player, LootParams lootParams, ItemStack stack) {
    }

    public static void addSeashellTooltip(ItemStack itemStack, Level world, List<Component> tooltip, TooltipFlag tooltipContext) {
    }

    public static boolean allowBottleSpawning() {
        return BeachpartyConfig.ALLOW_BOTTLE_SPAWNING.get();
    }

    public static int getBottleMaxCount() {
        return BeachpartyConfig.BOTTLE_MAX_COUNT.get();
    }

    public static int getBottleSpawnInterval() {
        return BeachpartyConfig.BOTTLE_SPAWN_INTERVAL.get();
    }

    public static <T extends Entity> Supplier<EntityType<T>> registerBoatType(String name,
                                                                               EntityType.EntityFactory<T> factory,
                                                                               MobCategory category,
                                                                               float width,
                                                                               float height,
                                                                               int clientTrackingRange) {
        RegistryObject<EntityType<T>> obj = ENTITY_TYPES.register(name,
                () -> EntityType.Builder.of(factory, category).sized(width, height).build(name));
        return obj::get;
    }

    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
