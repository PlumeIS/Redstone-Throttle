package com.z33awa.redstonethrottle;

import com.z33awa.redstonethrottle.registry.ModBlockEntities;
import com.z33awa.redstonethrottle.registry.ModBlocks;
import com.z33awa.redstonethrottle.registry.ModCreativeTabs;
import com.z33awa.redstonethrottle.registry.ModItems;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(RedstoneThrottleMod.MOD_ID)
public class RedstoneThrottleMod {

    public static final String MOD_ID = "redstone_throttle";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RedstoneThrottleMod(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.TABS.register(modEventBus);
    }
}
