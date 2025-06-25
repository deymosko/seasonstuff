package com.grape.grapes_ss.core.registry;


import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.block.*;
import com.grape.grapes_ss.core.entity.PalmBoatEntity;
import com.grape.grapes_ss.core.item.*;
import com.grape.grapes_ss.core.util.BeachpartyIdentifier;
import com.grape.grapes_ss.core.util.BeachpartyUtil;
import com.grape.grapes_ss.core.util.BeachpartyWoodType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ObjectRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Beachparty.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Beachparty.MOD_ID);

    public static final RegistryObject<Item> RAW_MUSSEL_MEAT = registerItem("raw_mussel_meat", () -> new Item(getSettings().food(Foods.POTATO)));
    public static final RegistryObject<Item> COOKED_MUSSEL_MEAT = registerItem("cooked_mussel_meat", () -> new Item(getSettings().food(Foods.BAKED_POTATO)));
    public static final RegistryObject<Item> RUBBER_RING_BLUE = registerItem("rubber_ring_blue", () -> new TrinketsArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.UNCOMMON), new BeachpartyIdentifier("textures/models/armor/rubber_ring_blue.png")));
    public static final RegistryObject<Item> RUBBER_RING_PINK = registerItem("rubber_ring_pink", () -> new TrinketsArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.UNCOMMON), new BeachpartyIdentifier("textures/models/armor/rubber_ring_pink.png")));
    public static final RegistryObject<Item> RUBBER_RING_STRIPPED = registerItem("rubber_ring_stripped", () -> new TrinketsArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.UNCOMMON), new BeachpartyIdentifier("textures/models/armor/rubber_ring_stripped.png")));
    public static final RegistryObject<Item> RUBBER_RING_PELICAN = registerItem("rubber_ring_pelican", () -> new TrinketsArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/models/armor/rubber_ring_pelican.png")));
    public static final RegistryObject<Item> RUBBER_RING_AXOLOTL = registerItem("rubber_ring_axolotl", () -> new TrinketsArmorItem(ArmorMaterialRegistry.RING, ArmorItem.Type.CHESTPLATE, getSettings().rarity(Rarity.RARE), new BeachpartyIdentifier("textures/models/armor/rubber_ring_axolotl.png")));
    public static final RegistryObject<Item> POOL_NOODLE = registerItem("pool_noodle", () -> new PoolNoodleItem(getSettings()));
    public static final RegistryObject<Item> CROCS = registerItem("crocs", () -> new DyeableBeachpartyArmorItem(ArmorMaterialRegistry.CROCS, ArmorItem.Type.BOOTS, 1048335, getSettings().rarity(Rarity.EPIC), new BeachpartyIdentifier("textures/models/armor/crocs.png")));
    public static final RegistryObject<Item> SWIM_WINGS = registerItem("swim_wings", () -> new DyeableBeachpartyArmorItem(ArmorMaterialRegistry.SWIM_WINGS, ArmorItem.Type.CHESTPLATE, 0xFF5800, getSettings(), new BeachpartyIdentifier("textures/models/armor/swim_wings.png")));
    public static final RegistryObject<Block> THATCH_STAIRS = registerWithItem("thatch_stairs", () -> new StairBlock(Blocks.HAY_BLOCK.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).sound(SoundType.GRASS)));
    public static final RegistryObject<SaplingBlock> PALM_SPROUT = registerWithItem("palm_sprout", PalmSproutBlock::new);
    public static final RegistryObject<Block> PALM_TABLE = registerWithItem("palm_table", () -> new PalmTableBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistryObject<Block> PALM_BAR = registerWithItem("palm_bar", () -> new PalmBarBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistryObject<Block> BEACH_CHAIR = registerWithItem("beach_chair", () -> new BeachChairBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    public static final RegistryObject<Block> PALM_BAR_STOOL = registerWithItem("palm_bar_stool", () -> new PalmBarStoolBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS)));
    // beach_parasol removed
    // beach_towel removed
    // radio removed
    public static final RegistryObject<Block> MESSAGE_IN_A_BOTTLE = registerWithoutItem("message_in_a_bottle", () -> new MessageInABottleBlock(BlockBehaviour.Properties.copy(Blocks.GLASS), Block.box(4.0f, 0.0f, 4.0f, 12.0f, 6.0f, 12.0f)));
    public static final RegistryObject<Item> MESSAGE_IN_A_BOTTLE_ITEM = registerItem("message_in_a_bottle", () -> new MessageInABottleItem(ObjectRegistry.MESSAGE_IN_A_BOTTLE.get(), getSettings()));
    public static final RegistryObject<Block> SEASHELL_BLOCK = registerWithoutItem("seashell_block", () -> new SeashellBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT).instabreak().noParticlesOnBreak()));
    public static final RegistryObject<Item> SEASHELL = registerItem("seashell", () -> new SeashellItem(SEASHELL_BLOCK.get(), getSettings()));
    public static final RegistryObject<Block> SAND_BUCKET_BLOCK_FILLED = registerWithoutItem("sand_bucket_block_filled", () -> new SandBucketBlock(BlockBehaviour.Properties.copy(Blocks.DECORATED_POT)));
    // sand_bucket_filled item removed
    public static final RegistryObject<Block> SAND_BUCKET_BLOCK_EMPTY = registerWithoutItem("sand_bucket_block_empty", () -> new SandBucketBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART)));
    // sand_bucket_empty item removed
    public static final RegistryObject<Block> SANDCASTLE = registerWithoutItem("sandcastle", () -> new SandBucketBlock.SandCastleBlock(BlockBehaviour.Properties.copy(Blocks.SAND)));
    public static final RegistryObject<Block> SAND_PILE = registerWithoutItem("sand_pile", () -> new SandBucketBlock.SandPileBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> COCONUT_COCKTAIL = registerCocktail("coconut_cocktail", () -> new CocktailBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD).noOcclusion().instabreak(), MobEffects.DAMAGE_BOOST, 600, () -> CocktailBlock.COCONUT_COCKTAIL_SHAPE), MobEffects.DAMAGE_BOOST);
    public static final RegistryObject<Block> SWEETBERRIES_COCKTAIL = registerCocktail("sweetberries_cocktail", MobEffects.ABSORPTION, CocktailBlock.SWEETBERRIES_COCKTAIL_SHAPE);
    public static final RegistryObject<Block> COCOA_COCKTAIL = registerCocktail("cocoa_cocktail", MobEffects.REGENERATION, CocktailBlock.COCOA_COCKTAIL_SHAPE);
    public static final RegistryObject<Block> PUMPKIN_COCKTAIL = registerCocktail("pumpkin_cocktail", MobEffects.FIRE_RESISTANCE, CocktailBlock.PUMPKIN_COCKTAIL_SHAPE);
    public static final RegistryObject<Block> HONEY_COCKTAIL = registerCocktail("honey_cocktail", MobEffects.DIG_SPEED, CocktailBlock.HONEY_COCKTAIL_SHAPE);
    public static final RegistryObject<Block> MELON_COCKTAIL = registerCocktail("melon_cocktail", MobEffects.LUCK, CocktailBlock.MELON_COCKTAIL_SHAPE);
    // beach_goal removed
    public static final RegistryObject<Block> PALM_TORCH = registerWithoutItem("palm_torch", () -> new TorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> PALM_WALL_TORCH = registerWithoutItem("palm_wall_torch", () -> new WallTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD).dropsLike(PALM_TORCH.get()), ParticleTypes.FLAME));
    public static final RegistryObject<Item> PALM_TORCH_ITEM = registerItem("palm_torch_item", () -> new StandingAndWallBlockItem(ObjectRegistry.PALM_TORCH.get(), ObjectRegistry.PALM_WALL_TORCH.get(), getSettings(), Direction.DOWN));
    public static final RegistryObject<Block> TALL_PALM_TORCH = registerWithItem("tall_palm_torch", () -> new TallPalmTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).noCollission().instabreak().lightLevel((state) -> 14).sound(SoundType.WOOD), ParticleTypes.FLAME));
    public static final RegistryObject<Block> SANDWAVES = registerWithItem("sandwaves", () -> new SandBlock(14406560, BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)));
    // palm sign removed
    // palm wall sign remains for compatibility
    // palm hanging sign removed
    // palm wall hanging sign remains for compatibility
    // palm sign item removed
    // palm hanging sign item removed
    // palm boat item removed
    // palm chest boat item removed
    // floaty boat item removed
    public static final RegistryObject<Item> FLOATY_CHEST_BOAT = ITEMS.register("floaty_chest_boat", () -> new PalmBoatItem(true, PalmBoatEntity.Type.FLOATY, new Item.Properties()));
    public static final RegistryObject<Block> BEACHPARTY_BANNER = registerWithItem("beachparty_banner", () -> new CompletionistBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BEACHPARTY_WALL_BANNER = registerWithoutItem("beachparty_wall_banner", () -> new CompletionistWallBannerBlock(BlockBehaviour.Properties.of().strength(1F).instrument(NoteBlockInstrument.BASS).noCollission().sound(SoundType.WOOD)));
    // overgrown_disc removed
    public static final RegistryObject<Item> MUSIC_DISC_BEACHPARTY = registerItem("music_disc_beachparty", () -> new RecordItem(1, SoundEventRegistry.BEACHPARTY.get(), getSettings().stacksTo(1), 2400));
    public static final RegistryObject<Item> MUSIC_DISC_CARIBBEAN_BEACH = registerItem("music_disc_caribbean_beach", () -> new RecordItem(1, SoundEventRegistry.CARIBBEAN_BEACH.get(), getSettings().stacksTo(1), 2400));
    public static final RegistryObject<Item> MUSIC_DISC_PRIDELANDS = registerItem("music_disc_pridelands", () -> new RecordItem(1, SoundEventRegistry.PRIDELANDS.get(), getSettings().stacksTo(1), 2400));
    public static final RegistryObject<Item> MUSIC_DISC_VOCALISTA = registerItem("music_disc_vocalista", () -> new RecordItem(1, SoundEventRegistry.VOCALISTA.get(), getSettings().stacksTo(1), 2400));
    public static final RegistryObject<Item> MUSIC_DISC_WILD_VEINS = registerItem("music_disc_wild_veins", () -> new RecordItem(1, SoundEventRegistry.WILD_VEINS.get(), getSettings().stacksTo(1), 2400));

    static Item.Properties getSettings() {
        return getSettings(settings -> {
        });
    }

    private static Item.Properties getSettings(Consumer<Item.Properties> consumer) {
        Item.Properties settings = new Item.Properties();
        consumer.accept(settings);
        return settings;
    }

    private static FoodProperties cocktailFoodComponent(MobEffect effect) {
        FoodProperties.Builder component = new FoodProperties.Builder().nutrition(1).saturationMod(1);
        if (effect != null) component.effect(new MobEffectInstance(effect, 900), 1.0f);
        return component.build();
    }

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private static RegistryObject<Block> registerCocktail(String name, MobEffect effect, VoxelShape shape) {
        return registerCocktail(name, () -> new CocktailBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion().instabreak(), effect, 600, () -> shape), effect);
    }

    private static <T extends Block> RegistryObject<T> registerCocktail(String name, Supplier<T> block, MobEffect effect) {
        RegistryObject<T> toReturn = registerWithoutItem(name, block);
        registerItem(name, () -> new DrinkBlockItem(toReturn.get(), getSettings(s -> s.food(cocktailFoodComponent(effect)))));
        return toReturn;
    }

    public static <T extends Block> RegistryObject<T> registerWithItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = registerWithoutItem(name, block);
        registerItem(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    public static <T extends Block> RegistryObject<T> registerWithoutItem(String path, Supplier<T> block) {
        return BLOCKS.register(path, block);
    }

    public static <T extends Item> RegistryObject<T> registerItem(String path, Supplier<T> itemSupplier) {
        return ITEMS.register(path, itemSupplier);
    }
}

