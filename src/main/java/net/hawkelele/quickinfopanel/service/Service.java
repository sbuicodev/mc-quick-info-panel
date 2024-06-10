package net.hawkelele.quickinfopanel.service;

import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.provider.ConfigProvider;

/**
 * Base event listener for the mod
 */
public abstract class Service<T> {
    private final Event<T> event = event();
    protected final Config config = ConfigProvider.getConfig();

    /**
     * The code that registers the service
     */
    public void register() {
        event.register(handle());
    }

    public abstract Event<T> event();
    public abstract T handle();
}
