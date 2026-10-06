package com.github.lvantic.za_mega_shards.itemGroup;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.item.ZAMSItems;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class ZAMSTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(ZAMegaShards.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB =
            TABS.register(
                    "za_mega_shards",
                    () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                            .title(Component.translatable("itemGroup.za_mega_shards"))
                            .icon(() -> ZAMSItems.MEGA_SHARD.get().getDefaultInstance())
                            .build()
            );

    public static void register() {
        TABS.register();
    }
}