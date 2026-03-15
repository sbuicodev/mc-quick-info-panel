package net.hawkelele.quickinfopanel;

import net.hawkelele.quickinfopanel.gui.elements.Panel;

import java.util.HashMap;
import java.util.Map;

public class PanelManager {
    private static Map<String, Panel> panels = new HashMap<>();

    public static void register(Panel panel, String name) {
        panels.put(name, panel);
    }

    public static Panel get(String name) {
        return panels.getOrDefault(name, null);
    }
}
