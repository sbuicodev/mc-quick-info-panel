package net.hawkelele.quickinfopanel.registries;

import net.hawkelele.quickinfopanel.services.Service;

import java.util.HashMap;

public class ServicesRegistry {
    public static HashMap<String, Service<?>> providersMap = new HashMap<>();

    /**
     * Registers the provided list of event handlers
     *
     * @param services The list of handlers for which the "register" method will be executed
     */
    public static void register(Service<?>... services) {
        for (Service<?> service : services) {
            service.register();
            providersMap.put(service.getClass().getName(), service);
        }
    }
}
