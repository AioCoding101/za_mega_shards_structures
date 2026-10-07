package com.github.lvantic.za_mega_shards.fabric;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.fabric.villager.ZAMSPointOfInterestTypesFabric;
import net.fabricmc.api.ModInitializer;

public final class ZAMegaShardsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ZAMegaShards.init();

        ZAMSPointOfInterestTypesFabric.register();
    }
}