package com.github.lvantic.za_mega_shards.villager;

import com.github.lvantic.za_mega_shards.ZAMegaShards;
import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.VillagerProfession;

public final class ZAMSVillagerProfessions {

    public static final DeferredRegister<VillagerProfession> PROFESSIONS =
            DeferredRegister.create(
                    ZAMegaShards.MOD_ID,
                    Registries.VILLAGER_PROFESSION
            );

    public static final ResourceLocation MEGA_RESEARCHER_ID =
            ResourceLocation.fromNamespaceAndPath(
                    ZAMegaShards.MOD_ID,
                    "mega_researcher"
            );

    public static final RegistrySupplier<VillagerProfession> MEGA_RESEARCHER =
            PROFESSIONS.register(
                    "mega_researcher",
                    () -> new VillagerProfession(
                            MEGA_RESEARCHER_ID.toString(),

                            holder -> holder.is(
                                    ZAMSPointOfInterestTypes.MEGA_RESEARCH_STATION_KEY
                            ),

                            holder -> holder.is(
                                    ZAMSPointOfInterestTypes.MEGA_RESEARCH_STATION_KEY
                            ),

                            ImmutableSet.of(),
                            ImmutableSet.of(),

                            SoundEvents.VILLAGER_WORK_LIBRARIAN
                    )
            );

    private ZAMSVillagerProfessions() {
    }

    public static void register() {
        PROFESSIONS.register();
    }
}