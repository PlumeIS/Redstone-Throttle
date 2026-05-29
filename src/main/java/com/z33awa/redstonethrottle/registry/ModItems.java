package com.z33awa.redstonethrottle.registry;

import com.z33awa.redstonethrottle.RedstoneThrottleMod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RedstoneThrottleMod.MOD_ID);

    public static final DeferredItem<BlockItem> REDSTONE_SPEED_MODULATOR =
        ITEMS.registerSimpleBlockItem(ModBlocks.REDSTONE_SPEED_MODULATOR, new Item.Properties());
}
