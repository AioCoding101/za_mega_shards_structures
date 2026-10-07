package com.github.lvantic.za_mega_shards.neoforge.villager;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public final class ZAMSPointOfInterestTypesNeoForge {

    private static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(
                    Registries.POINT_OF_INTEREST_TYPE,
                    ZAMegaShards.MOD_ID
            );

    public static final DeferredHolder<PoiType, PoiType>
            MEGA_RESEARCH_STATION =
            POI_TYPES.register(
                    "mega_research_station",
                    () -> new PoiType(
                            Set.copyOf(
                                    ZAMSBlocks.MEGA_RESEARCH_STATION
                                            .get()
                                            .getStateDefinition()
                                            .getPossibleStates()
                            ),
                            1,
                            1
                    )
            );

    private ZAMSPointOfInterestTypesNeoForge() {
    }

    public static void register(IEventBus modEventBus) {
        POI_TYPES.register(modEventBus);
    }
}