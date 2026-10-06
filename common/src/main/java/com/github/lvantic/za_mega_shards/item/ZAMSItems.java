package com.github.lvantic.za_mega_shards.item;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.itemGroup.ZAMSTabs;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ZAMSItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ZAMegaShards.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> MEGA_SHARD =
            ITEMS.register(
                    "mega_shard",
                    () -> new Item(
                            new Item.Properties()
                                    .arch$tab(ZAMSTabs.MAIN_TAB)
                    )
            );

    public static final RegistrySupplier<Item> MEGA_RESEARCH_STATION =
            ITEMS.register(
                    "mega_research_station",
                    () -> new BlockItem(
                            ZAMSBlocks.MEGA_RESEARCH_STATION.get(),
                            new Item.Properties()
                                    .arch$tab(ZAMSTabs.MAIN_TAB)
                    )
            );

    public static void register() {
        ITEMS.register();
    }
}