package com.github.lvantic.za_mega_shards.block.custom;

import com.github.lvantic.za_mega_shards.block.entity.MegaResearchStationBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MegaResearchStationBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING =
            BlockStateProperties.HORIZONTAL_FACING;

    public MegaResearchStationBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(
            BlockPlaceContext context
    ) {
        return this.defaultBlockState()
                .setValue(
                        FACING,
                        context.getHorizontalDirection().getOpposite()
                );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        return new MegaResearchStationBlockEntity(
                pos,
                state
        );
    }

    @Override
    public InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hitResult
    ) {
        if (!level.isClientSide) {
            BlockEntity blockEntity =
                    level.getBlockEntity(pos);

            if (
                    blockEntity instanceof MenuProvider menuProvider
                            && player instanceof ServerPlayer serverPlayer
            ) {
                serverPlayer.openMenu(
                        menuProvider
                );
            }
        }

        return InteractionResult.sidedSuccess(
                level.isClientSide
        );
    }

    @Override
    protected void onRemove(
            BlockState state,
            Level level,
            BlockPos pos,
            BlockState newState,
            boolean movedByPiston
    ) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity =
                    level.getBlockEntity(pos);

            if (blockEntity instanceof Container container) {
                Containers.dropContents(
                        level,
                        pos,
                        container
                );
            }
        }

        super.onRemove(
                state,
                level,
                pos,
                newState,
                movedByPiston
        );
    }

    @Override
    protected @NotNull BlockState rotate(
            BlockState state,
            Rotation rotation
    ) {
        return state.setValue(
                FACING,
                rotation.rotate(
                        state.getValue(FACING)
                )
        );
    }

    @Override
    protected @NotNull BlockState mirror(
            BlockState state,
            Mirror mirror
    ) {
        return state.rotate(
                mirror.getRotation(
                        state.getValue(FACING)
                )
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(FACING);
    }
}