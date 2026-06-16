package net.hawkelele.quickinfopanel.config;

import net.hawkelele.quickinfopanel.platform.Services;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Config {
    public static final List<String> PANEL_KEYS = List.of("coordinates", "compass", "clock", "opposite", "biome", "weather");

    public boolean displayMainPanel = true;
    public boolean displaySecondaryPanel = true;
    public String layout = "default";
    public Map<String, Boolean> panels = defaultPanels();
    public boolean debugBounds = false;

    private static Config latestConfig;

    public static Config read() {
        if (latestConfig == null) {
            latestConfig = normalize(Services.CONFIG.read());
        }
        return latestConfig;
    }

    public static void write(Function<Config, Config> change) throws IOException {
        Config current = read();
        latestConfig = normalize(change.apply(current));
        Services.CONFIG.save(latestConfig);
    }

    public static void write(Config config) throws IOException {
        write(_ -> config);
    }

    public static void refresh(Config config) {
        latestConfig = normalize(config);
    }

    public static boolean isSingleElementHidingEnabled() {
        return net.hawkelele.quickinfopanel.Constants.ENABLE_SINGLE_ELEMENT_HIDING;
    }

    public static boolean isPanelEnabled(String key) {
        return !isSingleElementHidingEnabled() || read().panels.getOrDefault(key, true);
    }

    private static Config normalize(Config config) {
        Config normalized = config != null ? config : new Config();
        Map<String, Boolean> mergedPanels = defaultPanels();
        if (normalized.panels != null) {
            normalized.panels.forEach((key, value) -> {
                if (value != null) {
                    mergedPanels.put(key, value);
                }
            });
        }
        if (!isSingleElementHidingEnabled()) {
            PANEL_KEYS.forEach(key -> mergedPanels.put(key, true));
        }
        normalized.panels = mergedPanels;
        return normalized;
    }

    private static Map<String, Boolean> defaultPanels() {
        Map<String, Boolean> panels = new LinkedHashMap<>();
        PANEL_KEYS.forEach(key -> panels.put(key, true));
        return panels;
    }
}
