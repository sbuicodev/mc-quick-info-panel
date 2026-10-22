package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.platform.Services;
import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.input.ToggleLayoutDebugKeybind;
import net.hawkelele.quickinfopanel.input.ToggleModKeybind;
import net.hawkelele.quickinfopanel.input.ToggleSecondaryPanelKeybind;

import java.util.Set;
import java.util.LinkedHashSet;

public class KeybindsRegistry {
    public static final Set<Keybind> KEYBINDS = buildKeybinds();

    private static Set<Keybind> buildKeybinds() {
        Set<Keybind> keybinds = new LinkedHashSet<>();
        keybinds.add(new ToggleModKeybind());
        keybinds.add(new ToggleSecondaryPanelKeybind());
        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            keybinds.add(new ToggleLayoutDebugKeybind());
        }
        return Set.copyOf(keybinds);
    }

    public static void registerKeybinds() {
        for (Keybind keybind : KEYBINDS) {
            keybind.register();
        }
    }
}
