package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleLayoutDebugHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleSecondaryPanelHandler;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ToggleSecondaryPanelKeybind implements Keybind {
    private final KeyMapping keyMapping = new KeyMapping(
            "key." + Constants.MOD_ID + ".togglealtinfo",
            GLFW.GLFW_KEY_N,
            Constants.KEYMAPPING_CATEGORY
    );

    @Override
    public KeyMapping getKeyMapping() {
        return keyMapping;
    }

    @Override
    public IClientEventHandler getHandler() {
        return new ToggleSecondaryPanelHandler();
    }
}
