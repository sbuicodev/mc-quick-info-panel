package net.hawkelele.quickinfopanel.platform.services;

import net.hawkelele.quickinfopanel.config.Config;

import java.io.IOException;

public interface IConfigStore {
    Config read();

    void save(Config config) throws IOException;
}
