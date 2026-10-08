package com.github.lvantic.za_mega_shards.client.renderer;

import com.github.lvantic.za_mega_shards.block.custom.MegaResearchStationBlock;
import com.github.lvantic.za_mega_shards.block.entity.MegaResearchStationBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MegaResearchStationRenderer
        implements BlockEntityRenderer<MegaResearchStationBlockEntity> {

    private final ItemRenderer itemRenderer;

    public MegaResearchStationRenderer(
            BlockEntityRendererProvider.Context context
    ) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(
            MegaResearchStationBlockEntity blockEntity,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay
    ) {
        ItemStack stack = blockEntity.getItem(
                MegaResearchStationBlockEntity.INPUT_SLOT
        );

        if (stack.isEmpty() || blockEntity.getLevel() == null) {
            return;
        }

        poseStack.pushPose();

        poseStack.translate(0.5D, 0.59D, 0.5D);

        Direction facing = blockEntity.getBlockState()
                .getValue(MegaResearchStationBlock.FACING);

        float rotation = switch (facing) {
            case NORTH -> 0.0F;
            case EAST -> -90.0F;
            case SOUTH -> 180.0F;
            case WEST -> -270.0F;
            default -> 0.0F;
        };

        poseStack.mulPose(
                Axis.YP.rotationDegrees(rotation)
        );

        poseStack.translate(0.0D, 0.0D, 0.0D);

        poseStack.mulPose(
                Axis.XP.rotationDegrees(90.0F)
        );

        poseStack.scale(0.4F, 0.4F, 0.4F);

        this.itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                packedLight,
                packedOverlay,
                poseStack,
                bufferSource,
                blockEntity.getLevel(),
                0
        );

        poseStack.popPose();
    }
}