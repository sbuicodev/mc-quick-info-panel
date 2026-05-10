package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.input.ToggleLayoutDebugKeybind;

import java.util.Set;

public class KeybindsRegistry {
    public static final Set<Keybind> KEYBINDS = Set.of(
            new ToggleLayoutDebugKeybind()
    );

    public static void registerKeybinds() {
        for (Keybind keybind : KEYBINDS) {
            keybind.register();
        }
    }
}
