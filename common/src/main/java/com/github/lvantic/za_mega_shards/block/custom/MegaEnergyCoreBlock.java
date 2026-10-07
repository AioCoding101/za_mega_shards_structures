package com.github.lvantic.za_mega_shards.block.custom;

import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class MegaEnergyCoreBlock extends AmethystBlock {

    public static final MapCodec<MegaEnergyCoreBlock> CODEC =
            simpleCodec(MegaEnergyCoreBlock::new);

    private static final int GROWTH_CHANCE = 5;

    private static final Direction[] DIRECTIONS =
            Direction.values();

    public MegaEnergyCoreBlock(
            BlockBehaviour.Properties properties
    ) {
        super(properties);
    }

    @Override
    public MapCodec<? extends MegaEnergyCoreBlock> codec() {
        return CODEC;
    }

    @Override
    protected void randomTick(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            RandomSource random
    ) {
        /*
         * Same basic chance as vanilla Budding Amethyst:
         *
         * 1 in 5 random ticks.
         */
        if (random.nextInt(GROWTH_CHANCE) != 0) {
            return;
        }

        /*
         * Pick one of the six faces of the core.
         */
        Direction growDirection =
                DIRECTIONS[random.nextInt(DIRECTIONS.length)];

        BlockPos growPos =
                pos.relative(growDirection);

        BlockState currentState =
                level.getBlockState(growPos);

        Block nextStage = null;

        /*
         * Empty space or source water
         * -> create a new Small Bud.
         */
        if (canMegaEnergyGrowAtState(currentState)) {

            nextStage =
                    ZAMSBlocks.SMALL_MEGA_ENERGY_BUD.get();

        }

        /*
         * Small -> Medium
         *
         * Only grow it if the bud is actually facing away
         * from this core.
         */
        else if (
                currentState.is(
                        ZAMSBlocks.SMALL_MEGA_ENERGY_BUD.get()
                )
                        && currentState.getValue(
                        AmethystClusterBlock.FACING
                ) == growDirection
        ) {

            nextStage =
                    ZAMSBlocks.MEDIUM_MEGA_ENERGY_BUD.get();

        }

        /*
         * Medium -> Large
         */
        else if (
                currentState.is(
                        ZAMSBlocks.MEDIUM_MEGA_ENERGY_BUD.get()
                )
                        && currentState.getValue(
                        AmethystClusterBlock.FACING
                ) == growDirection
        ) {

            nextStage =
                    ZAMSBlocks.LARGE_MEGA_ENERGY_BUD.get();

        }

        /*
         * Large -> Cluster
         */
        else if (
                currentState.is(
                        ZAMSBlocks.LARGE_MEGA_ENERGY_BUD.get()
                )
                        && currentState.getValue(
                        AmethystClusterBlock.FACING
                ) == growDirection
        ) {

            nextStage =
                    ZAMSBlocks.MEGA_ENERGY_CLUSTER.get();

        }

        if (nextStage == null) {
            return;
        }

        /*
         * Preserve source water when a bud grows into it.
         */
        boolean waterlogged =
                currentState
                        .getFluidState()
                        .getType() == Fluids.WATER;

        BlockState newState =
                nextStage.defaultBlockState()
                        .setValue(
                                AmethystClusterBlock.FACING,
                                growDirection
                        )
                        .setValue(
                                AmethystClusterBlock.WATERLOGGED,
                                waterlogged
                        );

        level.setBlockAndUpdate(
                growPos,
                newState
        );
    }

    public static boolean canMegaEnergyGrowAtState(
            BlockState state
    ) {
        return state.isAir()
                || (
                state.is(Blocks.WATER)
                        && state
                        .getFluidState()
                        .getAmount() == 8
        );
    }
}