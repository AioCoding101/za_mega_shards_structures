package com.github.lvantic.za_mega_shards;

import com.github.lvantic.za_mega_shards.block.ZAMSBlockEntities;
import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.item.ZAMSItems;
import com.github.lvantic.za_mega_shards.itemGroup.ZAMSTabs;
import com.github.lvantic.za_mega_shards.screen.ZAMSMenuTypes;

public final class ZAMegaShards {

    public static final String MOD_ID = "za_mega_shards";

    private ZAMegaShards() {
    }

    public static void init() {
        ZAMSBlocks.register();
        ZAMSBlockEntities.register();
        ZAMSItems.register();
        ZAMSMenuTypes.register();
        ZAMSTabs.register();
    }
}