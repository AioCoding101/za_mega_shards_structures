package com.github.lvantic.za_mega_shards.block;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.block.entity.MegaResearchStationBlockEntity;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ZAMSBlockEntities {

    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ZAMegaShards.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<MegaResearchStationBlockEntity>>
            MEGA_RESEARCH_STATION =
            BLOCK_ENTITIES.register(
                    "mega_research_station",
                    () -> BlockEntityType.Builder.of(
                            MegaResearchStationBlockEntity::new,
                            ZAMSBlocks.MEGA_RESEARCH_STATION.get()
                    ).build(null)
            );

    private ZAMSBlockEntities() {
    }

    public static void register() {
        BLOCK_ENTITIES.register();
    }
}