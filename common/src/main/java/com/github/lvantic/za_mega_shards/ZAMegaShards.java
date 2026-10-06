package com.github.lvantic.za_mega_shards;

/**
 * Common entrypoint for ZA Mega Shards.
 *
 * Keep gameplay registrations and shared logic here (or in common subpackages).
 * Fabric/NeoForge entrypoints should only bootstrap this common code unless a
 * loader-specific API is genuinely required.
 */
public final class ZAMegaShards {
    public static final String MOD_ID = "za_mega_shards";

    private ZAMegaShards() {
    }

    public static void init() {
        // Registries will be added here next.
    }
}
