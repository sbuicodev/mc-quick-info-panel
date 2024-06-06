package net.hawkelele.quickinfopanel.registries;

import net.hawkelele.quickinfopanel.providers.ProviderInterface;

import java.util.HashMap;

public class ProvidersRegistry {
    public static HashMap<String, ProviderInterface> providersMap = new HashMap<>();

    /**
     * Registers the provided list of event handlers
     *
     * @param providers The list of handlers for which the "register" method will be executed
     */
    public static void register(ProviderInterface... providers) {
        for (ProviderInterface provider : providers) {
            provider.register();
            providersMap.put(provider.getClass().getName(), provider);
        }
    }
}
