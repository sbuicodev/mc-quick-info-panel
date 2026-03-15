package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.layouts.DefaultLayout;
import net.hawkelele.quickinfopanel.gui.layouts.TopLeftLayout;

import java.util.Map;

public class LayoutRegistry {
    private final static Map<String, Layout> layouts = Map.ofEntries(
            Map.entry("default", new DefaultLayout()),
            Map.entry("top-left", new TopLeftLayout())
    );

    public static Layout get(String key) {
        return layouts.getOrDefault(key, new DefaultLayout());
    }

    public static String[] list() {
        return layouts.keySet().toArray(new String[0]);
    }
}
