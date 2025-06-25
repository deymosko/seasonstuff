package com.grape.grapes_ss.core.event;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.block.BeachParasolBlock;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = Beachparty.MOD_ID)
public class CommonEvents {

    public static void init() {
    }

    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        LoottableInjector.InjectLoot(event.getName(), event.getTable());
    }

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        Entity entity = event.getTarget();
        InteractionHand hand = event.getHand();
        ItemStack itemInHand = player.getItemInHand(hand);

        if (itemInHand.getItem() == ObjectRegistry.POOL_NOODLE.get()) {
            Vec3 knockbackDirection = new Vec3(entity.getX() - player.getX(), 0.6, entity.getZ() - player.getZ()).normalize().scale(1.5);

            entity.push(knockbackDirection.x, knockbackDirection.y, knockbackDirection.z);

            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PARROT_IMITATE_SLIME, SoundSource.PLAYERS, 1.0F, 1.5F);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        if (entity.level().isClientSide()) return;

        if (isFireDamage(source) && isNearBeachParasol(entity)) {
            float reducedDamage = amount * 0.96f;
            event.setAmount(reducedDamage);
        }
    }

    private static boolean isFireDamage(DamageSource source) {
        return source.is(DamageTypes.IN_FIRE) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.LAVA);
    }

    private static boolean isNearBeachParasol(LivingEntity entity) {
        Level level = entity.level();
        BlockPos entityPos = entity.blockPosition();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos checkPos = entityPos.offset(x, y, z);
                    BlockState blockState = level.getBlockState(checkPos);
                    if (blockState.getBlock() instanceof BeachParasolBlock) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static class LoottableInjector {
        public static void InjectLoot(ResourceLocation id, net.minecraft.world.level.storage.loot.LootTable table) {
            String prefix = "minecraft:chests/";
            String name = id.toString();

            if (name.startsWith(prefix)) {
                String file = name.substring(name.indexOf(prefix) + prefix.length());
                switch (file) {
                    case "desert_pyramid", "buried_treasure", "shipwreck_supply", "shipwreck_treasure",
                         "simple_dungeon", "underwater_ruin_big", "underwater_ruin_small", "woodland_mansion" ->
                            table.addPool(getPool(file));
                    default -> {
                    }
                }
            }
        }

        public static LootPool getPool(String entryName) {
            return LootPool.lootPool().add(getPoolEntry(entryName)).build();
        }

        private static LootPoolEntryContainer.Builder<?> getPoolEntry(String name) {
            ResourceLocation table = new ResourceLocation(Beachparty.MOD_ID, "chests/" + name);
            return LootTableReference.lootTableReference(table);
        }
    }
}
