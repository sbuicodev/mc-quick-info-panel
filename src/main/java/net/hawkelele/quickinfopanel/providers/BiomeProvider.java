package net.hawkelele.quickinfopanel.providers;

public interface BiomeProvider {
    /**
     * @return the current biome's identifier, e.g. minecraft:sulfur_caves
     */
    String getBiomeId();

    /**
     * @return the biome's translated name, or null when there's no translation for it
     */
    String getTranslation(String biomeId);
}
