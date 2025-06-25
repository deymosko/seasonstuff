package com.grape.grapes_ss.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.block.entity.*;
import com.grape.grapes_ss.core.entity.*;
import com.grape.grapes_ss.core.util.BeachpartyIdentifier;
import com.grape.grapes_ss.platform.PlatformHelper;

import java.util.function.Supplier;

import static com.grape.grapes_ss.core.registry.ObjectRegistry.*;

public class EntityTypeRegistry {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Beachparty.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Beachparty.MOD_ID);

    public static final RegistryObject<EntityType<ChairEntity>> CHAIR = registerEntity("chair", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.001F, 0.001F).build(new BeachpartyIdentifier("chair").toString()));
    public static final RegistryObject<EntityType<ThrowableCoconutEntity>> COCONUT = registerEntity("coconut", () -> EntityType.Builder.<ThrowableCoconutEntity>of(ThrowableCoconutEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new BeachpartyIdentifier("coconut").toString()));
    public static final Supplier<EntityType<PalmBoatEntity>> PALM_BOAT = PlatformHelper.registerBoatType("palm_boat", PalmBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<PalmChestBoatEntity>> PALM_CHEST_BOAT = PlatformHelper.registerBoatType("palm_chest_boat", PalmChestBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);

    // beachparty sign removed
    // beachparty hanging sign removed
    public static final RegistryObject<BlockEntityType<PalmCabinetBlockEntity>> CABINET_BLOCK_ENTITY = registerBlockEntity("cabinet", () -> BlockEntityType.Builder.of(PalmCabinetBlockEntity::new, Blocks.BAMBOO_PLANKS).build(null));
    public static final RegistryObject<BlockEntityType<PalmBarBlockEntity>> PALM_BAR_BLOCK_ENTITY = registerBlockEntity("palm_bar", () -> BlockEntityType.Builder.of(PalmBarBlockEntity::new, PALM_BAR.get()).build(null));
    // beach_goal block entity removed
    public static final RegistryObject<BlockEntityType<WetHayBaleBlockEntity>> WET_HAY_BALE_BLOCK_ENTITY = registerBlockEntity("wet_hay_bale", () -> BlockEntityType.Builder.of(WetHayBaleBlockEntity::new, Blocks.HAY_BLOCK).build(null));
    public static final RegistryObject<BlockEntityType<CompletionistBannerEntity>> BEACHPARTY_BANNER = registerBlockEntity("beachparty_banner", () -> BlockEntityType.Builder.of(CompletionistBannerEntity::new, ObjectRegistry.BEACHPARTY_BANNER.get(), ObjectRegistry.BEACHPARTY_WALL_BANNER.get()).build(null));
    // radio block entity removed

    private static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(path, type);
    }

    private static <T extends EntityType<?>> RegistryObject<T> registerEntity(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(path, type);
    }

    public static void registerAttributes() {
    }

    public static void init() {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        registerAttributes();
    }
}