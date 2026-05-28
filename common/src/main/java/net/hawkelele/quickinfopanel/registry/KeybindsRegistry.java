package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.input.ToggleLayoutDebugKeybind;
import net.hawkelele.quickinfopanel.input.ToggleModKeybind;
import net.hawkelele.quickinfopanel.input.ToggleSecondaryPanelKeybind;

import java.util.Set;

public class KeybindsRegistry {
    public static final Set<Keybind> KEYBINDS = Set.of(
            new ToggleModKeybind(),
            new ToggleSecondaryPanelKeybind(),
            new ToggleLayoutDebugKeybind()
    );

    public static void registerKeybinds() {
        for (Keybind keybind : KEYBINDS) {
            keybind.register();
        }
    }
}
