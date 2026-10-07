package com.github.lvantic.za_mega_shards.screen;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.screen.custom.handler.MegaResearchStationMenu;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class ZAMSMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(
                    ZAMegaShards.MOD_ID,
                    Registries.MENU
            );

    public static final RegistrySupplier<MenuType<MegaResearchStationMenu>>
            MEGA_RESEARCH_STATION =
            registerMenuType(
                    "mega_research_station",
                    MegaResearchStationMenu::new
            );

    private ZAMSMenuTypes() {
    }

    private static <T extends AbstractContainerMenu>
    RegistrySupplier<MenuType<T>> registerMenuType(
            String name,
            MenuType.MenuSupplier<T> factory
    ) {
        return MENUS.register(
                name,
                () -> new MenuType<>(
                        factory,
                        FeatureFlags.DEFAULT_FLAGS
                )
        );
    }

    public static void register() {
        MENUS.register();
    }
}