package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;

import java.util.HashMap;
import java.util.Map;

public class PanelRegistry {
    private final static Map<String, Layout> panels = new HashMap<>();

    public static void register(Layout panel, String name) {
        panels.put(name, panel);
    }

    public static Layout get(String name) {
        return panels.getOrDefault(name, null);
    }
}
