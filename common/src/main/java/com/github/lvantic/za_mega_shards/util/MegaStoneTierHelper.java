package com.github.lvantic.za_mega_shards.util;

import com.github.lvantic.za_mega_shards.tag.ZAMSItemTags;
import net.minecraft.world.item.ItemStack;

public final class MegaStoneTierHelper {

    public static final int TIER_1_BUY_PRICE = 64;
    public static final int TIER_2_BUY_PRICE = 80;
    public static final int TIER_3_BUY_PRICE = 96;
    public static final int TIER_4_BUY_PRICE = 112;
    public static final int TIER_5_BUY_PRICE = 128;

    public static final int TIER_1_SHARD_RETURN = 32;
    public static final int TIER_2_SHARD_RETURN = 40;
    public static final int TIER_3_SHARD_RETURN = 48;
    public static final int TIER_4_SHARD_RETURN = 56;
    public static final int TIER_5_SHARD_RETURN = 64;

    private MegaStoneTierHelper() {
    }

    public static boolean isMegaStone(ItemStack stack) {
        return stack.is(ZAMSItemTags.MEGA_STONES);
    }

    public static int getTier(ItemStack stack) {
        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_5)) {
            return 5;
        }

        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_4)) {
            return 4;
        }

        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_3)) {
            return 3;
        }

        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_2)) {
            return 2;
        }

        if (stack.is(ZAMSItemTags.MEGA_STONE_TIER_1)) {
            return 1;
        }

        return 0;
    }

    public static int getBuyPrice(ItemStack stack) {
        return switch (getTier(stack)) {
            case 1 -> TIER_1_BUY_PRICE;
            case 2 -> TIER_2_BUY_PRICE;
            case 3 -> TIER_3_BUY_PRICE;
            case 4 -> TIER_4_BUY_PRICE;
            case 5 -> TIER_5_BUY_PRICE;
            default -> 0;
        };
    }

    public static int getShardReturn(ItemStack stack) {
        return switch (getTier(stack)) {
            case 1 -> TIER_1_SHARD_RETURN;
            case 2 -> TIER_2_SHARD_RETURN;
            case 3 -> TIER_3_SHARD_RETURN;
            case 4 -> TIER_4_SHARD_RETURN;
            case 5 -> TIER_5_SHARD_RETURN;
            default -> 0;
        };
    }
}