package com.grape.grapes_ss;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import com.grape.grapes_ss.Beachparty;
import com.grape.grapes_ss.core.block.BeachSunLounger;
import com.grape.grapes_ss.core.block.BeachTowelBlock;
import com.grape.grapes_ss.core.registry.CompostablesRegistry;
import com.grape.grapes_ss.core.registry.ObjectRegistry;
import com.grape.grapes_ss.client.integration.CuriosWearableTrinket;
import com.grape.grapes_ss.registry.BeachpartyConfig;
import com.grape.grapes_ss.registry.BeachpartyVillagers;
import com.grape.grapes_ss.platform.PlatformHelper;

@Mod(Beachparty.MOD_ID)
public class BeachpartyForge {
    public BeachpartyForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BeachpartyVillagers.register(modEventBus);
        PlatformHelper.init();
        BeachpartyConfig.loadConfig(BeachpartyConfig.COMMON_CONFIG,
                FMLPaths.CONFIGDIR.get().resolve("beachparty.toml").toString());

        Beachparty.init();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::enqueueIMC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(CompostablesRegistry::init);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }


    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockState state = event.getLevel().getBlockState(event.getPos());
    }

    @Mod.EventBusSubscriber(modid = Beachparty.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEventsHandler {

        @SubscribeEvent
        public static void playerSetSpawn(PlayerSetSpawnEvent event) {
            Level level = event.getEntity().level();

            if (event.getNewSpawn() != null) {
                Block block = level.getBlockState(event.getNewSpawn()).getBlock();

                if (!level.isClientSide && (block instanceof BeachTowelBlock || block instanceof BeachSunLounger) && !event.isForced()) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
