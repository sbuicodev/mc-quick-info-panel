package net.hawkelele.quickinfopanel.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ToggleInfoOnKeypress extends EventHandler<ClientTickEvents.EndTick> {
    private static KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.quickinfopanel.toggle", // The translation key of the keybinding's name
            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
            GLFW.GLFW_KEY_B, // The keycode of the key
            "category.quickinfopanel.text" // The translation key of the keybinding's category.
    ));

    @Override
    public Event<ClientTickEvents.EndTick> event() {
        return ClientTickEvents.END_CLIENT_TICK;
    }

    @Override
    public ClientTickEvents.EndTick handle() {
        return client -> {
            if (client.player == null) {
                return;
            }

            while (keyBinding.wasPressed()) {
                config.update((config) -> config.displayPanel = !config.displayPanel);
            }
        };
    }
}
