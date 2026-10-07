package com.github.lvantic.za_mega_shards.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MegaEnergyCoreBlock extends AmethystBlock {

    public static final MapCodec<MegaEnergyCoreBlock> CODEC =
            simpleCodec(MegaEnergyCoreBlock::new);

    public MegaEnergyCoreBlock(
            BlockBehaviour.Properties properties
    ) {
        super(properties);
    }

    @Override
    public MapCodec<? extends MegaEnergyCoreBlock> codec() {
        return CODEC;
    }
}