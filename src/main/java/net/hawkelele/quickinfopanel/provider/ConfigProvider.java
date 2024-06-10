package net.hawkelele.quickinfopanel.provider;

import net.hawkelele.quickinfopanel.config.Config;

public class ConfigProvider {
    private static final Config config = new Config();

    public static Config getConfig() {
        return config;
    }
}
