package com.github.lvantic.za_mega_shards;

import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.item.ZAMSItems;
import com.github.lvantic.za_mega_shards.itemGroup.ZAMSTabs;

public final class ZAMegaShards {

    public static final String MOD_ID = "za_mega_shards";

    private ZAMegaShards() {
    }

    public static void init() {
        ZAMSBlocks.register();
        ZAMSItems.register();
        ZAMSTabs.register();
    }
}