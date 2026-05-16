package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleLayoutDebugHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleModHandler;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ToggleModKeybind implements Keybind {
    private final KeyMapping keyMapping = new KeyMapping(
            "key." + Constants.MOD_ID + ".toggle",
            GLFW.GLFW_KEY_B,
            Constants.KEYMAPPING_CATEGORY
    );

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public IClientEventHandler getHandler() {
        return new ToggleModHandler();
    }
}
