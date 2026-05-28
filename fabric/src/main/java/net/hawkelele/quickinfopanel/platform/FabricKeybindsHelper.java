package net.hawkelele.quickinfopanel.platform;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.platform.services.IKeybindsHelper;

public class FabricKeybindsHelper implements IKeybindsHelper {
    @Override
    public void registerKeybind(Keybind keybind) {
        KeyMappingHelper.registerKeyMapping(keybind.getKeyMapping());
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (keybind.getKeyMapping().consumeClick()) {
                keybind.getHandler().accept(client);
            }
        });
    }
}
