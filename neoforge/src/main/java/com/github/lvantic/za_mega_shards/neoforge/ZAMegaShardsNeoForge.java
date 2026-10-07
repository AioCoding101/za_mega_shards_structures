package com.github.lvantic.za_mega_shards.neoforge;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.neoforge.villager.ZAMSPointOfInterestTypesNeoForge;
import com.github.lvantic.za_mega_shards.villager.ZAMSVillagerTrades;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(ZAMegaShards.MOD_ID)
public final class ZAMegaShardsNeoForge {

    public ZAMegaShardsNeoForge(
            IEventBus modEventBus
    ) {
        ZAMSPointOfInterestTypesNeoForge.register(
                modEventBus
        );

        ZAMegaShards.init();

        modEventBus.addListener(
                this::commonSetup
        );
    }

    private void commonSetup(
            FMLCommonSetupEvent event
    ) {
        event.enqueueWork(
                ZAMSVillagerTrades::register
        );
    }
}