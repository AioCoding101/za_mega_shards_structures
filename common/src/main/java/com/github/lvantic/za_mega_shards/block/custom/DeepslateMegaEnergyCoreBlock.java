package com.github.lvantic.za_mega_shards.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class DeepslateMegaEnergyCoreBlock
        extends MegaEnergyCoreBlock {

    public static final MapCodec<DeepslateMegaEnergyCoreBlock> CODEC =
            simpleCodec(DeepslateMegaEnergyCoreBlock::new);

    public DeepslateMegaEnergyCoreBlock(
            BlockBehaviour.Properties properties
    ) {
        super(properties);

        this.registerDefaultState(
                this.defaultBlockState()
                        .setValue(
                                RotatedPillarBlock.AXIS,
                                Direction.Axis.Y
                        )
        );
    }

    @Override
    public MapCodec<DeepslateMegaEnergyCoreBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(
                RotatedPillarBlock.AXIS
        );
    }

    @Override
    public BlockState getStateForPlacement(
            BlockPlaceContext context
    ) {
        return this.defaultBlockState()
                .setValue(
                        RotatedPillarBlock.AXIS,
                        context.getClickedFace().getAxis()
                );
    }

    @Override
    protected BlockState rotate(
            BlockState state,
            Rotation rotation
    ) {
        return RotatedPillarBlock.rotatePillar(
                state,
                rotation
        );
    }
}