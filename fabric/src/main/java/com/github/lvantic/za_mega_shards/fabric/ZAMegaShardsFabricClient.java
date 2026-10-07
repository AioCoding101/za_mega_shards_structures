package com.github.lvantic.za_mega_shards.fabric;

import com.github.lvantic.za_mega_shards.screen.ZAMSMenuTypes;
import com.github.lvantic.za_mega_shards.screen.custom.screen.MegaResearchStationScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public final class ZAMegaShardsFabricClient
        implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        MenuScreens.register(
                ZAMSMenuTypes.MEGA_RESEARCH_STATION.get(),
                MegaResearchStationScreen::new
        );
    }
}