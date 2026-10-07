package com.github.lvantic.za_mega_shards.fabric.villager;

import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.villager.ZAMSPointOfInterestTypes;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;

public final class ZAMSPointOfInterestTypesFabric {

    private ZAMSPointOfInterestTypesFabric() {
    }

    public static void register() {
        PointOfInterestHelper.register(
                ZAMSPointOfInterestTypes.MEGA_RESEARCH_STATION_ID,
                1,
                1,
                ZAMSBlocks.MEGA_RESEARCH_STATION
                        .get()
                        .getStateDefinition()
                        .getPossibleStates()
        );
    }
}