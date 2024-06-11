package net.hawkelele.quickinfopanel.event;

import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.config.Config;

/**
 * Base event listener for the mod
 */
public abstract class EventHandler<T> {
    private final Event<T> event = event();
    protected final Config config = Config.getInstance();

    /**
     * The code that registers the service
     */
    public void register() {
        event.register(handle());
    }

    public abstract Event<T> event();

    public abstract T handle();
}
