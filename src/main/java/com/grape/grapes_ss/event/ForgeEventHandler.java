package com.grape.grapes_ss.forge.event;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import com.grape.grapes_ss.forge.client.integration.CuriosWearableTrinket;
import com.grape.grapes_ss.forge.registry.BeachpartyVillagers;


import java.util.List;

@Mod.EventBusSubscriber(modid = Beachparty.MOD_ID)
public class ForgeEventHandler {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType().equals(BeachpartyVillagers.SANDYMERCHANT.get())) {

            List<VillagerTrades.ItemListing> level1 = event.getTrades().computeIfAbsent(1, k -> new java.util.ArrayList<>());
            level1.add(new BasicItemListing(8, new ItemStack(ObjectRegistry.PALM_SPROUT.get()), 1, 3, 0.25F));
            level1.add(new BasicItemListing(4, new ItemStack(ObjectRegistry.PALM_TORCH_ITEM.get()), 17, 4, 0.25F));

            List<VillagerTrades.ItemListing> level2 = event.getTrades().computeIfAbsent(2, k -> new java.util.ArrayList<>());
            level2.add(new BasicItemListing(3, new ItemStack(ObjectRegistry.SANDWAVES.get()), 4, 2, 0.35F));
            level2.add(new BasicItemListing(27, new ItemStack(ObjectRegistry.POOL_NOODLE.get()), 1, 10, 0.75F));

            List<VillagerTrades.ItemListing> level3 = event.getTrades().computeIfAbsent(3, k -> new java.util.ArrayList<>());
            // removed items

            List<VillagerTrades.ItemListing> level4 = event.getTrades().computeIfAbsent(4, k -> new java.util.ArrayList<>());
            level4.add(new BasicItemListing(12, new ItemStack(ObjectRegistry.COCONUT_COCKTAIL.get()), 1, 4, 0.45F));

            List<VillagerTrades.ItemListing> level5 = event.getTrades().computeIfAbsent(5, k -> new java.util.ArrayList<>());
            level5.add(new BasicItemListing(61, new ItemStack(ObjectRegistry.RUBBER_RING_BLUE.get()), 1, 12, 0.25F));
            level5.add(new BasicItemListing(58, new ItemStack(ObjectRegistry.RUBBER_RING_PINK.get()), 1, 12, 0.25F));
            level5.add(new BasicItemListing(22, new ItemStack(ObjectRegistry.MESSAGE_IN_A_BOTTLE_ITEM.get()), 1, 4, 0.25F));
            // floaty boat removed
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (event.getSource() == null) return;

        if (!event.getSource().is(DamageTypes.ON_FIRE) && !event.getSource().is(DamageTypes.IN_FIRE)) return;

        float reduction = 0;

        if (reduction > 0) {
            float newDamage = event.getAmount() * (1 - Math.min(reduction, 1.0f));
            event.setAmount(newDamage);
        }
    }
}