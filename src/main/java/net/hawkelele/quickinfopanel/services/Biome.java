package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.BiomeProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Biome {
    private final BiomeProvider biomeProvider;

    public Biome(BiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;
    }

    /**
     * Shows the translated biome name, or one made from its identifier when there's no
     * translation (e.g. a biome added in a newer game version or by another mod)
     */
    public String getBiomeName() {
        String id = biomeProvider.getBiomeId();
        String translation = biomeProvider.getTranslation(id);
        return translation != null ? translation : nameFromId(id);
    }

    /**
     * Turns the last part of a biome identifier into capitalized words
     *
     * @return e.g. "Sulfur Caves" for minecraft:sulfur_caves
     */
    static String nameFromId(String id) {
        String path = id.substring(Math.max(id.lastIndexOf(':'), id.lastIndexOf('/')) + 1);
        return Arrays.stream(path.split("_"))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
