package com.z33awa.redstonethrottle.client;

import com.z33awa.redstonethrottle.content.modulator.RedstoneSpeedModulatorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;

import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneSpeedModulatorRenderer extends KineticBlockEntityRenderer<RedstoneSpeedModulatorBlockEntity> {

    public RedstoneSpeedModulatorRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(RedstoneSpeedModulatorBlockEntity be, float partialTicks, PoseStack ms,
                              MultiBufferSource buffer, int light, int overlay) {
        BlockState state = be.getBlockState();
        Block block = state.getBlock();
        Axis shaftAxis = ((IRotate) block).getRotationAxis(state);
        BlockPos pos = be.getBlockPos();
        float time = AnimationTickHolder.getRenderTime(be.getLevel());
        float baseSpeed = be.getSpeed();
        float positionOffset = getRotationOffsetForPosition(be, pos, shaftAxis);

        // Calculate each half from its actual RPM. Create's stock SplitShaftRenderer applies
        // the modifier after reducing the input angle modulo 360, which is only phase-safe for
        // integral ratios such as 0 and +/-1. This block supports arbitrary output ratios, so
        // applying the modifier to speed first keeps each half aligned with its neighbouring
        // shaft. getSpeed() also becomes zero while overstressed.
        for (Direction direction : Iterate.directionsInAxis(shaftAxis)) {
            float renderedSpeed = baseSpeed * be.getRotationSpeedModifier(direction);
            float angle = ((time * renderedSpeed * 3f / 10f + positionOffset) % 360f)
                / 180f * (float) Math.PI;

            SuperByteBuffer shaft = CachedBuffers.partialFacing(AllPartialModels.SHAFT_HALF, state, direction);
            kineticRotationTransform(shaft, be, shaftAxis, angle, light);
            shaft.renderInto(ms, buffer.getBuffer(RenderType.solid()));
        }
    }
}
