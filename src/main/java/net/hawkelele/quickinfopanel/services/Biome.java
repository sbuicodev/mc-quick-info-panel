package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.BiomeProvider;

public class Biome {
    private final BiomeProvider biomeProvider;

    public Biome(BiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;
    }

    public String getBiomeName() {
        return biomeProvider.getBiome();
    }

}
