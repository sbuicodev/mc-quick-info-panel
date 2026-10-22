package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleModHandler;
import net.minecraft.client.KeyMapping;
import org.lwjgl.sdl.SDLKeycode;

public class ToggleModKeybind implements Keybind {
    private final KeyMapping keyMapping = new KeyMapping(
            "key." + Constants.MOD_ID + ".toggle",
            SDLKeycode.SDLK_B,
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
