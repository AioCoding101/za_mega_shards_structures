package com.github.lvantic.za_mega_shards.villager.trade;

import com.github.lvantic.za_mega_shards.item.ZAMSItems;
import com.github.lvantic.za_mega_shards.util.MegaStoneTierHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RandomMegaStoneTrade implements VillagerTrades.ItemListing {

    private static final float BONUS_TRADE_CHANCE = 0.5F;

    private final TagKey<Item> tierTag;
    private final int maxUses;
    private final int villagerXp;
    private final boolean canGenerateBonusTrade;

    public RandomMegaStoneTrade(
            TagKey<Item> tierTag,
            int maxUses,
            int villagerXp,
            boolean canGenerateBonusTrade
    ) {
        this.tierTag = tierTag;
        this.maxUses = maxUses;
        this.villagerXp = villagerXp;
        this.canGenerateBonusTrade = canGenerateBonusTrade;
    }

    @Override
    public @Nullable MerchantOffer getOffer(
            Entity entity,
            RandomSource random
    ) {
        if (!(entity instanceof Villager villager)) {
            return null;
        }

        Item selectedStone = this.getRandomStone(
                villager,
                random,
                null
        );

        if (selectedStone == null) {
            return null;
        }

        /*
         * Vanilla normally generates two trades per villager level.
         *
         * One of our two listings has a 50% chance to manually
         * add one extra unique Mega Stone trade, giving us
         * either 2 or 3 Mega Stone offers per rank.
         */
        if (
                this.canGenerateBonusTrade
                        && random.nextFloat() < BONUS_TRADE_CHANCE
        ) {
            Item bonusStone = this.getRandomStone(
                    villager,
                    random,
                    selectedStone
            );

            if (bonusStone != null) {
                MerchantOffer bonusOffer =
                        this.createOffer(bonusStone);

                if (bonusOffer != null) {
                    villager.getOffers().add(
                            bonusOffer
                    );
                }
            }
        }

        return this.createOffer(selectedStone);
    }

    private @Nullable Item getRandomStone(
            Villager villager,
            RandomSource random,
            @Nullable Item excludedItem
    ) {
        List<Item> candidates = new ArrayList<>();

        /*
         * Read the tag at the moment the trade is generated.
         *
         * Enabling datapacks to add custom Mega Stones without Java
         */
        for (
                Holder<Item> holder :
                BuiltInRegistries.ITEM.getTagOrEmpty(this.tierTag)
        ) {
            Item item = holder.value();

            if (item == excludedItem) {
                continue;
            }

            if (this.isAlreadyOffered(villager, item)) {
                continue;
            }

            candidates.add(item);
        }

        if (candidates.isEmpty()) {
            return null;
        }

        return candidates.get(
                random.nextInt(candidates.size())
        );
    }

    private boolean isAlreadyOffered(
            Villager villager,
            Item item
    ) {
        return villager.getOffers()
                .stream()
                .anyMatch(
                        offer ->
                                offer.getResult().getItem() == item
                );
    }

    private @Nullable MerchantOffer createOffer(
            Item megaStone
    ) {
        ItemStack result =
                new ItemStack(megaStone);

        int price =
                MegaStoneTierHelper.getBuyPrice(result);

        if (price <= 0) {
            return null;
        }

        int primaryAmount =
                Math.min(price, 64);

        int secondaryAmount =
                Math.max(price - 64, 0);

        ItemCost primaryCost =
                new ItemCost(
                        ZAMSItems.MEGA_SHARD.get(),
                        primaryAmount
                );

        Optional<ItemCost> secondaryCost =
                secondaryAmount > 0
                        ? Optional.of(
                        new ItemCost(
                                ZAMSItems.MEGA_SHARD.get(),
                                secondaryAmount
                        )
                )
                        : Optional.empty();

        return new MerchantOffer(
                primaryCost,
                secondaryCost,
                result,
                this.maxUses,
                this.villagerXp,
                0.0F
        );
    }
}