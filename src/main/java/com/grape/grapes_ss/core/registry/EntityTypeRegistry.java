package net.satisfy.beachparty.core.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.core.block.entity.*;
import net.satisfy.beachparty.core.entity.*;
import net.satisfy.beachparty.core.util.BeachpartyIdentifier;
import net.satisfy.beachparty.platform.PlatformHelper;

import java.util.function.Supplier;

import static net.satisfy.beachparty.core.registry.ObjectRegistry.*;

public class EntityTypeRegistry {
    private static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registries.BLOCK_ENTITY_TYPE).getRegistrar();
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Beachparty.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<ChairEntity>> CHAIR = registerEntity("chair", () -> EntityType.Builder.of(ChairEntity::new, MobCategory.MISC).sized(0.001F, 0.001F).build(new BeachpartyIdentifier("chair").toString()));
    public static final RegistrySupplier<EntityType<ThrowableCoconutEntity>> COCONUT = registerEntity("coconut", () -> EntityType.Builder.<ThrowableCoconutEntity>of(ThrowableCoconutEntity::new, MobCategory.MISC).sized(0.25f, 0.25f).build(new BeachpartyIdentifier("coconut").toString()));
    public static final Supplier<EntityType<PalmBoatEntity>> PALM_BOAT = PlatformHelper.registerBoatType("palm_boat", PalmBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);
    public static final Supplier<EntityType<PalmChestBoatEntity>> PALM_CHEST_BOAT = PlatformHelper.registerBoatType("palm_chest_boat", PalmChestBoatEntity::new, MobCategory.MISC, 1.375F, 0.5625F, 10);

    // beachparty sign removed
    // beachparty hanging sign removed
    public static final RegistrySupplier<BlockEntityType<PalmCabinetBlockEntity>> CABINET_BLOCK_ENTITY = registerBlockEntity("cabinet", () -> BlockEntityType.Builder.of(PalmCabinetBlockEntity::new, Blocks.BAMBOO_PLANKS).build(null));
    public static final RegistrySupplier<BlockEntityType<PalmBarBlockEntity>> PALM_BAR_BLOCK_ENTITY = registerBlockEntity("palm_bar", () -> BlockEntityType.Builder.of(PalmBarBlockEntity::new, PALM_BAR.get()).build(null));
    // beach_goal block entity removed
    public static final RegistrySupplier<BlockEntityType<WetHayBaleBlockEntity>> WET_HAY_BALE_BLOCK_ENTITY = registerBlockEntity("wet_hay_bale", () -> BlockEntityType.Builder.of(WetHayBaleBlockEntity::new, Blocks.HAY_BLOCK).build(null));
    public static final RegistrySupplier<BlockEntityType<CompletionistBannerEntity>> BEACHPARTY_BANNER = registerBlockEntity("beachparty_banner", () -> BlockEntityType.Builder.of(CompletionistBannerEntity::new, ObjectRegistry.BEACHPARTY_BANNER.get(), ObjectRegistry.BEACHPARTY_WALL_BANNER.get()).build(null));
    // radio block entity removed

    private static <T extends BlockEntityType<?>> RegistrySupplier<T> registerBlockEntity(final String path, final Supplier<T> type) {
        return BLOCK_ENTITY_TYPES.register(new BeachpartyIdentifier(path), type);
    }

    private static <T extends EntityType<?>> RegistrySupplier<T> registerEntity(final String path, final Supplier<T> type) {
        return ENTITY_TYPES.register(path, type);
    }

    public static void registerAttributes() {
    }

    public static void init() {
        ENTITY_TYPES.register();
        registerAttributes();
    }
}