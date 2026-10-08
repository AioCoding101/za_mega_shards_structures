package com.github.lvantic.za_mega_shards.util;

import com.github.lvantic.za_mega_shards.config.ZAMSConfig;
import com.github.lvantic.za_mega_shards.tag.ZAMSItemTags;
import net.minecraft.world.item.ItemStack;

public final class MegaStoneTierHelper {

    private MegaStoneTierHelper() {
    }

    public static boolean isMegaStone(ItemStack stack) {
        return stack.is(ZAMSItemTags.MEGA_STONES);
    }

    public static int getTier(ItemStack stack) {
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_5)) return 5;
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_4)) return 4;
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_3)) return 3;
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_2)) return 2;
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_1)) return 1;
        return 0;
    }

    public static int getBuyPrice(ItemStack stack) {
        return ZAMSConfig.get().getBuyPrice(getTier(stack));
    }

    public static int getShardReturn(ItemStack stack) {
        return ZAMSConfig.get().getShardReturn(getTier(stack));
    }
}