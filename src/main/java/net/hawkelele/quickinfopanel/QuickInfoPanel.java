package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.commands.NetherCommand;
import net.hawkelele.quickinfopanel.registries.CommandsRegistry;
import net.hawkelele.quickinfopanel.registries.ServicesRegistry;
import net.hawkelele.quickinfopanel.services.TogglePanelOnKeypressService;


public class QuickInfoPanel implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ServicesRegistry.register(
//                new PanelHudRenderCallbackProvider(),
//                new HidePanelOnGameMessageService(),
                new TogglePanelOnKeypressService()
        );

        CommandsRegistry.register(
                new NetherCommand()
        );
    }



}
