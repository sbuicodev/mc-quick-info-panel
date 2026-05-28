package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.config.LayoutPreset;
import net.hawkelele.quickinfopanel.gui.layouts.DefaultLayout;
import net.hawkelele.quickinfopanel.gui.layouts.BottomLeftLayout;
import net.hawkelele.quickinfopanel.gui.layouts.BottomRightLayout;
import net.hawkelele.quickinfopanel.gui.layouts.TopLeftLayout;
import net.hawkelele.quickinfopanel.gui.layouts.TopRightLayout;

import java.util.Map;

public class LayoutRegistry {
    private final static Map<String, Layout> layouts = Map.ofEntries(
            Map.entry("default", new DefaultLayout()),
            Map.entry("top-left", new TopLeftLayout()),
            Map.entry("top-right", new TopRightLayout()),
            Map.entry("bottom-left", new BottomLeftLayout()),
            Map.entry("bottom-right", new BottomRightLayout())
    );

    public static Layout get(String key) {
        return layouts.getOrDefault(key, new DefaultLayout());
    }

    public static String[] list() {
        return java.util.Arrays.stream(LayoutPreset.values()).map(LayoutPreset::id).toArray(String[]::new);
    }
}
