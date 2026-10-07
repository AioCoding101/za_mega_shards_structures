package com.github.lvantic.za_mega_shards.tag;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ZAMSItemTags {

    public static final TagKey<Item> MEGA_STONES = create("mega_stones");

    public static final TagKey<Item> MEGA_STONE_TIER_1 = create("mega_stone_tier_1");
    public static final TagKey<Item> MEGA_STONE_TIER_2 = create("mega_stone_tier_2");
    public static final TagKey<Item> MEGA_STONE_TIER_3 = create("mega_stone_tier_3");
    public static final TagKey<Item> MEGA_STONE_TIER_4 = create("mega_stone_tier_4");
    public static final TagKey<Item> MEGA_STONE_TIER_5 = create("mega_stone_tier_5");

    private ZAMSItemTags() {
    }

    private static TagKey<Item> create(String name) {
        return TagKey.create(
                Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(
                        ZAMegaShards.MOD_ID,
                        name
                )
        );
    }
}