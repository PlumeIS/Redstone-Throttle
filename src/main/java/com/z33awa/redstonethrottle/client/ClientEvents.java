package com.z33awa.redstonethrottle.client;

import com.z33awa.redstonethrottle.RedstoneThrottleMod;
import com.z33awa.redstonethrottle.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.transmission.SplitShaftVisual;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = RedstoneThrottleMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerVisuals(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
            SimpleBlockEntityVisualizer.builder(ModBlockEntities.REDSTONE_SPEED_MODULATOR.get())
                .factory(SplitShaftVisual::new)
                .skipVanillaRender(blockEntity -> true)
                .apply());
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
            ModBlockEntities.REDSTONE_SPEED_MODULATOR.get(),
            RedstoneSpeedModulatorRenderer::new);
    }
}
