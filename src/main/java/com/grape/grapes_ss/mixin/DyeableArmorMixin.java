package com.grape.grapes_ss.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import com.grape.grapes_ss.core.item.DyeableBeachpartyArmorItem;
import com.grape.grapes_ss.core.registry.ArmorRegistry;
import com.grape.grapes_ss.model.DyedArmorModelWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(DyeableBeachpartyArmorItem.class)
public abstract class DyeableArmorMixin extends DyeableArmorItem {

    @Shadow
    public abstract int getColor(@NotNull ItemStack stack);

    @Shadow
    private ResourceLocation getTexture;

    private DyeableArmorMixin(Type type, Properties properties) {
        super(null , type, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull Model getGenericArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                Model baseModel;
                if (slot == EquipmentSlot.CHEST) {
                    baseModel = ArmorRegistry.chestplateModel(stack.getItem(), original.body, original.leftArm, original.rightArm);
                } else if (slot == EquipmentSlot.LEGS) {
                    baseModel = ArmorRegistry.LeggingsModel(stack.getItem(), original.body, original.leftLeg, original.rightLeg);
                } else if (slot == EquipmentSlot.FEET) {
                    baseModel = ArmorRegistry.LeggingsModel(stack.getItem(), original.body, original.leftLeg, original.rightLeg);
                } else {
                    baseModel = original;
                }
                int dyeColor = getColor(stack);
                return new DyedArmorModelWrapper<>((EntityModel<LivingEntity>) baseModel, dyeColor);
            }
        });
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return getTexture.toString();
    }
}
