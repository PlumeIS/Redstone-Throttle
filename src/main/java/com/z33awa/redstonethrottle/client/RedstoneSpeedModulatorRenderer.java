package com.z33awa.redstonethrottle.client;

import com.z33awa.redstonethrottle.content.modulator.RedstoneSpeedModulatorBlock;
import com.z33awa.redstonethrottle.content.modulator.RedstoneSpeedModulatorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneSpeedModulatorRenderer extends KineticBlockEntityRenderer<RedstoneSpeedModulatorBlockEntity> {

    public RedstoneSpeedModulatorRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(RedstoneSpeedModulatorBlockEntity be, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {
        BlockState state = be.getBlockState();
        Direction facing = state.getValue(RedstoneSpeedModulatorBlock.FACING);
        BlockPos pos = be.getBlockPos();
        float time = AnimationTickHolder.getRenderTime(be.getLevel());

        // Front half — output side, spinning at our generated speed.
        // Use the modulator's own position offset for proper connection to downstream shaft.
        Direction frontDir = facing;
        BlockState frontState = state.setValue(RedstoneSpeedModulatorBlock.FACING, frontDir);
        float frontOffset = KineticBlockEntityRenderer.getRotationOffsetForPosition(be, pos, frontDir.getAxis());
        float outputSpeed = be.getOutputSpeed();
        renderShaftHalf(be, frontState, ms, buffer, light, frontDir, outputSpeed, time, frontOffset);

        // Back half — input side, spinning at the upstream network's speed.
        // The back shaft connects to the block behind us. For the rotation offset to properly
        // synchronise with the neighbour's shaft at the block boundary, we pass the neighbour's
        // position so that the offset step between us and the neighbour is correct.
        Direction backDir = facing.getOpposite();
        BlockState backState = state.setValue(RedstoneSpeedModulatorBlock.FACING, backDir);
        BlockPos neighbourPos = pos.relative(backDir);
        float backOffset = KineticBlockEntityRenderer.getRotationOffsetForPosition(be, neighbourPos, backDir.getAxis());
        float inputSpeed = be.getInputSpeed();
        renderShaftHalf(be, backState, ms, buffer, light, backDir, inputSpeed, time, backOffset);
    }

    private static void renderShaftHalf(RedstoneSpeedModulatorBlockEntity be, BlockState state, PoseStack ms,
                                        MultiBufferSource buffer, int light, Direction direction,
                                        float speed, float time, float positionOffset) {
        SuperByteBuffer shaft = CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, state, direction);
        float angle = ((time * speed * 3f / 10f + positionOffset) % 360f) / 180f * (float) Math.PI;
        KineticBlockEntityRenderer.kineticRotationTransform(shaft, be, direction.getAxis(), angle, light);
        shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
    }
}
