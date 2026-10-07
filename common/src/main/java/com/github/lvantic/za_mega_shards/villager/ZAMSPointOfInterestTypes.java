package com.github.lvantic.za_mega_shards.villager;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public final class ZAMSPointOfInterestTypes {

    public static final ResourceLocation MEGA_RESEARCH_STATION_ID =
            ResourceLocation.fromNamespaceAndPath(
                    ZAMegaShards.MOD_ID,
                    "mega_research_station"
            );

    public static final ResourceKey<PoiType> MEGA_RESEARCH_STATION_KEY =
            ResourceKey.create(
                    Registries.POINT_OF_INTEREST_TYPE,
                    MEGA_RESEARCH_STATION_ID
            );

    private ZAMSPointOfInterestTypes() {
    }
}