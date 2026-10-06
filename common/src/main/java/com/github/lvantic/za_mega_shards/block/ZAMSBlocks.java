package com.github.lvantic.za_mega_shards.block;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.block.custom.MegaResearchStationBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ZAMSBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ZAMegaShards.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> MEGA_RESEARCH_STATION =
            BLOCKS.register(
                    "mega_research_station",
                    () -> new MegaResearchStationBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.COLOR_GRAY)
                                    .strength(3.5F)
                                    .requiresCorrectToolForDrops()
                                    .sound(SoundType.METAL)
                    )
            );

    public static void register() {
        BLOCKS.register();
    }
}