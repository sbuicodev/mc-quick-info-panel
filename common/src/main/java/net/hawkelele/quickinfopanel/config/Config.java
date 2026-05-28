package net.hawkelele.quickinfopanel.config;

import net.hawkelele.quickinfopanel.platform.Services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Config {
    public boolean displayMainPanel = true;
    public boolean displaySecondaryPanel = true;
    public String layout = "default";
    public Map<String, Boolean> panels = new HashMap<String, Boolean>() {{
        put("coordinates", true);
        put("compass", true);
        put("clock", true);
        put("opposite", true);
        put("biome", true);
        put("weather", true);
    }};
    public boolean debugBounds = false;

    private static Config latestConfig;

    public static Config read() {
        if (latestConfig == null) {
            latestConfig = Services.CONFIG.read();
        }
        return latestConfig;
    }

    public static void write(Function<Config, Config> change) throws IOException {
        Config current = read();
        latestConfig = change.apply(current);
        Services.CONFIG.save(latestConfig);
    }

    public static void write(Config config) throws IOException {
        write(_ -> config);
    }

    public static void refresh(Config config) {
        latestConfig = config;
    }
}
