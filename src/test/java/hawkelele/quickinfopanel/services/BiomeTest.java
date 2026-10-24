package hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.BiomeProvider;
import net.hawkelele.quickinfopanel.services.Biome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BiomeTest {
    private static class MockBiomeProvider implements BiomeProvider {
        private final Map<String, String> translations = Map.of("minecraft:plains", "Pianure");
        private String biomeId = "minecraft:plains";

        public void setBiomeId(String biomeId) {
            this.biomeId = biomeId;
        }

        @Override
        public String getBiomeId() {
            return biomeId;
        }

        @Override
        public String getTranslation(String biomeId) {
            return translations.get(biomeId);
        }
    }

    @Test
    void testTranslatedName() {
        MockBiomeProvider biomeProvider = new MockBiomeProvider();
        Biome biome = new Biome(biomeProvider);

        Assertions.assertEquals("Pianure", biome.getBiomeName());
    }

    @Test
    void testNameFromIdWithoutTranslation() {
        MockBiomeProvider biomeProvider = new MockBiomeProvider();
        Biome biome = new Biome(biomeProvider);

        biomeProvider.setBiomeId("minecraft:sulfur_caves");
        Assertions.assertEquals("Sulfur Caves", biome.getBiomeName());

        biomeProvider.setBiomeId("minecraft:badlands");
        Assertions.assertEquals("Badlands", biome.getBiomeName());

        biomeProvider.setBiomeId("terralith:moonlight_grove");
        Assertions.assertEquals("Moonlight Grove", biome.getBiomeName());

        biomeProvider.setBiomeId("somemod:caves/deep_old_dark");
        Assertions.assertEquals("Deep Old Dark", biome.getBiomeName());

        biomeProvider.setBiomeId("somemod:__odd__name_");
        Assertions.assertEquals("Odd Name", biome.getBiomeName());
    }
}
