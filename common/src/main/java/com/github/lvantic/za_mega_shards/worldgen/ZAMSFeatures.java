package com.github.lvantic.za_mega_shards.worldgen;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.github.lvantic.za_mega_shards.worldgen.feature.MegaEnergyDepositFeature;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public final class ZAMSFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(
                    ZAMegaShards.MOD_ID,
                    Registries.FEATURE
            );

    public static final RegistrySupplier<MegaEnergyDepositFeature>
            MEGA_ENERGY_DEPOSIT =
            FEATURES.register(
                    "mega_energy_deposit",
                    () -> new MegaEnergyDepositFeature(
                            NoneFeatureConfiguration.CODEC
                    )
            );

    private ZAMSFeatures() {
    }

    public static void register() {
        FEATURES.register();
    }
}