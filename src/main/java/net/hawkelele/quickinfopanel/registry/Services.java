package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.event.EventHandler;

import java.util.HashMap;

public class Services {
    public static final HashMap<String, EventHandler<?>> providersMap = new HashMap<>();

    /**
     * Registers the provided list of event handlers
     *
     * @param services The list of handlers for which the "register" method will be executed
     */
    public static void register(EventHandler<?>... services) {
        for (EventHandler<?> service : services) {
            service.register();
            providersMap.put(service.getClass().getName(), service);
        }
    }
}
