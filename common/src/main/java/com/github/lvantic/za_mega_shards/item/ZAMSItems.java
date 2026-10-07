package com.github.lvantic.za_mega_shards.item;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.block.ZAMSBlocks;
import com.github.lvantic.za_mega_shards.itemGroup.ZAMSTabs;
import com.github.lvantic.za_mega_shards.item.custom.MegaResearchStationBlockItem;
import com.github.lvantic.za_mega_shards.item.custom.MegaShardItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ZAMSItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ZAMegaShards.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> MEGA_SHARD =
            ITEMS.register(
                    "mega_shard",
                    () -> new MegaShardItem(
                            new Item.Properties()
                                    .arch$tab(ZAMSTabs.MAIN_TAB)
                    )
            );

    public static final RegistrySupplier<Item> MEGA_RESEARCH_STATION =
            ITEMS.register(
                    "mega_research_station",
                    () -> new MegaResearchStationBlockItem(
                            ZAMSBlocks.MEGA_RESEARCH_STATION.get(),
                            new Item.Properties()
                                    .arch$tab(ZAMSTabs.MAIN_TAB)
                    )
            );

    public static final RegistrySupplier<Item> MEGA_SHARD_BLOCK =
            registerBlockItem(
                    "mega_shard_block",
                    ZAMSBlocks.MEGA_SHARD_BLOCK
            );

    public static final RegistrySupplier<Item> MEGA_ENERGY_CORE =
            registerBlockItem(
                    "mega_energy_core",
                    ZAMSBlocks.MEGA_ENERGY_CORE
            );

    public static final RegistrySupplier<Item> DEEPSLATE_MEGA_ENERGY_CORE =
            registerBlockItem(
                    "deepslate_mega_energy_core",
                    ZAMSBlocks.DEEPSLATE_MEGA_ENERGY_CORE
            );

    public static final RegistrySupplier<Item> SMALL_MEGA_ENERGY_BUD =
            registerBlockItem(
                    "small_mega_energy_bud",
                    ZAMSBlocks.SMALL_MEGA_ENERGY_BUD
            );

    public static final RegistrySupplier<Item> MEDIUM_MEGA_ENERGY_BUD =
            registerBlockItem(
                    "medium_mega_energy_bud",
                    ZAMSBlocks.MEDIUM_MEGA_ENERGY_BUD
            );

    public static final RegistrySupplier<Item> LARGE_MEGA_ENERGY_BUD =
            registerBlockItem(
                    "large_mega_energy_bud",
                    ZAMSBlocks.LARGE_MEGA_ENERGY_BUD
            );

    public static final RegistrySupplier<Item> MEGA_ENERGY_CLUSTER =
            registerBlockItem(
                    "mega_energy_cluster",
                    ZAMSBlocks.MEGA_ENERGY_CLUSTER
            );

    private static RegistrySupplier<Item> registerBlockItem(
            String name,
            RegistrySupplier<Block> block
    ) {
        return ITEMS.register(
                name,
                () -> new BlockItem(
                        block.get(),
                        new Item.Properties()
                                .arch$tab(ZAMSTabs.MAIN_TAB)
                )
        );
    }

    public static void register() {
        ITEMS.register();
    }
}