package com.github.lvantic.za_mega_shards.worldgen;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class ZAMSWorldGeneration {

    public static final ResourceKey<PlacedFeature>
            MEGA_ENERGY_DEPOSIT =
            ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    ResourceLocation.fromNamespaceAndPath(
                            ZAMegaShards.MOD_ID,
                            "mega_energy_deposit"
                    )
            );

    private ZAMSWorldGeneration() {
    }

    public static void register() {

        LifecycleEvent.SETUP.register(
                () ->
                        BiomeModifications.addProperties(
                                context ->
                                        context.hasTag(
                                                BiomeTags.IS_OVERWORLD
                                        ),
                                (context, mutable) ->
                                        mutable
                                                .getGenerationProperties()
                                                .addFeature(
                                                        GenerationStep
                                                                .Decoration
                                                                .UNDERGROUND_DECORATION,
                                                        MEGA_ENERGY_DEPOSIT
                                                )
                        )
        );
    }
}