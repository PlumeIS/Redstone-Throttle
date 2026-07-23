package com.z33awa.redstonethrottle.network;

import com.z33awa.redstonethrottle.RedstoneThrottleMod;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = RedstoneThrottleMod.MOD_ID)
public class NetworkRegistry {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("2");
        registrar.playToServer(
            UpdateModulatorPacket.TYPE,
            UpdateModulatorPacket.STREAM_CODEC,
            UpdateModulatorPacket::handle);
    }
}
