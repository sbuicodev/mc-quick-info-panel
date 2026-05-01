package net.hawkelele.quickinfopanel.config;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public Map<String, Boolean> panels = new HashMap<String, Boolean>() {{
        put("coordinates", true);
        put("compass", true);
        put("clock", true);
        put("opposite", true);
        put("biome", true);
        put("weather", true);
    }};

    public String layout = "default";
}
