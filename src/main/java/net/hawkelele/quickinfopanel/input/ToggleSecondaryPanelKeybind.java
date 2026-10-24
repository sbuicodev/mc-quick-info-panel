package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.handlers.ToggleSecondaryPanelHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class ToggleSecondaryPanelKeybind implements Keybind {
    private final KeyMapping keyMapping = new KeyMapping(
            "key." + Constants.MOD_ID + ".togglealtinfo",
            InputConstants.KEY_N,
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
