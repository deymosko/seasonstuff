package net.satisfy.beachparty.core.registry;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.satisfy.beachparty.Beachparty;
import net.satisfy.beachparty.core.recipe.MiniFridgeRecipe;
import net.satisfy.beachparty.core.recipe.PalmBarRecipe;

import java.util.function.Supplier;

public class RecipeRegistry {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Beachparty.MOD_ID);
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, Beachparty.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PalmBarRecipe>> PALM_BAR_RECIPE_SERIALIZER = create("palm_bar_mixing", PalmBarRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<PalmBarRecipe>> PALM_BAR_RECIPE_TYPE = create("palm_bar_mixing");

    public static final RegistryObject<RecipeSerializer<MiniFridgeRecipe>> MINI_FRIDGE_RECIPE_SERIALIZER = create("mini_fridge_freezing", MiniFridgeRecipe.Serializer::new);
    public static final RegistryObject<RecipeType<MiniFridgeRecipe>> MINI_FRIDGE_RECIPE_TYPE = create("mini_fridge_freezing");



    private static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> create(String name, Supplier<RecipeSerializer<T>> serializer) {
        return RECIPE_SERIALIZERS.register(name, serializer);
    }

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> create(String name) {
        Supplier<RecipeType<T>> type = () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        return RECIPE_TYPES.register(name, type);
    }

    public static void init() {
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
