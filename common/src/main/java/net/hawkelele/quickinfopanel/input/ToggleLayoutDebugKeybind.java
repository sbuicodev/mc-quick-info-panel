package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleLayoutDebugHandler;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ToggleLayoutDebugKeybind implements Keybind {
    private final KeyMapping keyMapping = new KeyMapping(
            "key." + Constants.MOD_ID + ".debug",
            GLFW.GLFW_KEY_M,
            Constants.KEYMAPPING_CATEGORY
    );

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public IClientEventHandler getHandler() {
        return new ToggleLayoutDebugHandler();
    }
}
