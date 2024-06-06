package net.hawkelele.quickinfopanel.providers;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;


import java.io.IOException;

public class PanelToggleKeyListener implements ProviderInterface {
    private static final KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.quickinfopanel.toggle", // The translation key of the keybinding's name
            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
            GLFW.GLFW_KEY_N, // The keycode of the key
            "category.quickinfopanel.test" // The translation key of the keybinding's category.
    ));

    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) {
                return;
            }

            while (keyBinding.wasPressed()) {
                try {
                    Config.update((config) -> config.displayPanel = !config.displayPanel);
                } catch (IOException e) {
                    client.player.sendMessage(Text.literal("[QIP] Something went wrong, please retry"));
                }
            }
        });
    }
}
