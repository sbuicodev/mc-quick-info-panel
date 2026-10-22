package net.hawkelele.quickinfopanel.platform.services;

import net.hawkelele.quickinfopanel.handlers.IClientEventHandler;
import net.hawkelele.quickinfopanel.input.Keybind;
import net.minecraft.client.KeyMapping;

public interface IKeybindsHelper<T> {
    void registerKeybind(Keybind keybind);
}
