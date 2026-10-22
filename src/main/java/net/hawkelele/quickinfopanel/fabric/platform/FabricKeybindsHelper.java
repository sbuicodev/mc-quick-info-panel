package net.hawkelele.quickinfopanel.fabric.platform;

//? if fabric {
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//? if >=26.1 {
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
//?} else {
/*import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
*///?}
import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.platform.services.IKeybindsHelper;

public class FabricKeybindsHelper implements IKeybindsHelper {
    @Override
    public void registerKeybind(Keybind keybind) {
        //? if >=26.1 {
        KeyMappingHelper.registerKeyMapping(keybind.getKeyMapping());
        //?} else {
        /*KeyBindingHelper.registerKeyBinding(keybind.getKeyMapping());
        *///?}
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            while (keybind.getKeyMapping().consumeClick()) {
                keybind.getHandler().accept(client);
            }
        });
    }
}
//?}
