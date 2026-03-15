package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.events.EventHandler;

import java.util.HashMap;

public class KeybindRegistry {
    public static final HashMap<String, EventHandler<?>> providersMap = new HashMap<>();

    /**
     * Registers the provided list of event handlers
     *
     * @param keybinds The list of handlers for which the "register" method will be executed
     */
    public static void register(EventHandler<?>... keybinds) {
        for (EventHandler<?> keybind : keybinds) {
            keybind.register();
            providersMap.put(keybind.getClass().getName(), keybind);
        }
    }
}
