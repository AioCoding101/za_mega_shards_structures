package com.github.lvantic.za_mega_shards.villager;

import com.github.lvantic.za_mega_shards.tag.ZAMSItemTags;
import com.github.lvantic.za_mega_shards.villager.trade.RandomMegaStoneTrade;
import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ZAMSVillagerTrades {

    /*
     * Temporary balancing values.
     *
     * We can tune max uses and XP later without touching
     * the actual trade generation logic.
     */
    private static final int MAX_USES = 4;

    private static final int NOVICE_XP = 2;
    private static final int APPRENTICE_XP = 10;
    private static final int JOURNEYMAN_XP = 20;
    private static final int EXPERT_XP = 30;
    private static final int MASTER_XP = 30;

    private static boolean registered = false;

    private ZAMSVillagerTrades() {
    }

    public static void register() {
        if (registered) {
            return;
        }

        registered = true;

        registerTier(
                1,
                ZAMSItemTags.MEGA_STONE_TIER_1,
                NOVICE_XP
        );

        registerTier(
                2,
                ZAMSItemTags.MEGA_STONE_TIER_2,
                APPRENTICE_XP
        );

        registerTier(
                3,
                ZAMSItemTags.MEGA_STONE_TIER_3,
                JOURNEYMAN_XP
        );

        registerTier(
                4,
                ZAMSItemTags.MEGA_STONE_TIER_4,
                EXPERT_XP
        );

        /*
         *   Master Villager only gets one trade
         */

        TradeRegistry.registerVillagerTrade(
                ZAMSVillagerProfessions.MEGA_RESEARCHER.get(),
                5,
                new RandomMegaStoneTrade(
                        ZAMSItemTags.MEGA_STONE_TIER_5,
                        MAX_USES,
                        MASTER_XP,
                        false
                )
        );
    }

    private static void registerTier(
            int villagerLevel,
            TagKey<Item> tierTag,
            int villagerXp
    ) {
        TradeRegistry.registerVillagerTrade(
                ZAMSVillagerProfessions.MEGA_RESEARCHER.get(),
                villagerLevel,

                /*
                 * First guaranteed trade.
                 *
                 * This one has the 50% chance of creating
                 * the optional third trade.
                 */
                new RandomMegaStoneTrade(
                        tierTag,
                        MAX_USES,
                        villagerXp,
                        true
                ),

                /*
                 * Second guaranteed trade.
                 */
                new RandomMegaStoneTrade(
                        tierTag,
                        MAX_USES,
                        villagerXp,
                        false
                )
        );
    }
}