package com.github.lvantic.za_mega_shards.neoforge;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ZAMegaShards.MOD_ID)
public final class ZAMegaShardsNeoForge {
    public ZAMegaShardsNeoForge(IEventBus modEventBus) {
        ZAMegaShards.init();
    }
}
