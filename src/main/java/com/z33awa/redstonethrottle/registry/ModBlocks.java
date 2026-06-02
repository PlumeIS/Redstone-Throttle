package com.z33awa.redstonethrottle.registry;

import com.z33awa.redstonethrottle.RedstoneThrottleMod;
import com.z33awa.redstonethrottle.content.modulator.RedstoneSpeedModulatorBlock;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RedstoneThrottleMod.MOD_ID);

    public static final DeferredBlock<RedstoneSpeedModulatorBlock> REDSTONE_SPEED_MODULATOR =
        BLOCKS.register("transmission",
            () -> new RedstoneSpeedModulatorBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.METAL)
                .strength(2.0f, 6.0f)
                .sound(SoundType.METAL)
                .noOcclusion()));
}
