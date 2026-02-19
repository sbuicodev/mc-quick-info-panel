package net.hawkelele.quickinfopanel.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ToggleInfoOnKeypress extends EventHandler<ClientTickEvents.EndTick> {
    private static final KeyMapping keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.quickinfopanel.toggle", // The translation key of the keybinding's name
            InputConstants.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
            GLFW.GLFW_KEY_B, // The keycode of the key
            QuickInfoPanel.KEYBINDCATEGORY // The translation key of the keybinding's category.
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

            while (keyBinding.consumeClick()) {
                config.update((config) -> config.displayPanel = !config.displayPanel);
            }
        };
    }
}
