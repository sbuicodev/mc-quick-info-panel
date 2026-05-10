package net.hawkelele.quickinfopanel.input;

import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.platform.Services;
import net.minecraft.client.KeyMapping;

public interface Keybind {
    KeyMapping getKeyMapping();
    IClientEventHandler getHandler();

    default void register() {
        Services.KEYBINDS.registerKeybind(this);
    }
}
