package net.hawkelele.quickinfopanel.service;

import net.fabricmc.fabric.api.event.Event;

/**
 * Base event listener for the mod
 */
public abstract class Service<T> {
    private Event<T> event = event();
    /**
     * The code that registers the service
     */
    public void register() {
        event.register(handle());
    }

    public abstract Event<T> event();
    public abstract T handle();
}
