package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.command.NetherCommand;
import net.hawkelele.quickinfopanel.command.PositionCommand;
import net.hawkelele.quickinfopanel.registry.CommandsRegistry;
import net.hawkelele.quickinfopanel.registry.ServicesRegistry;
import net.hawkelele.quickinfopanel.service.TogglePanelOnKeypressService;


public class QuickInfoPanel implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ServicesRegistry.register(
//                new PanelHudRenderCallbackProvider(),
//                new HidePanelOnGameMessageService(),
                new TogglePanelOnKeypressService()
        );

        CommandsRegistry.register(
                new NetherCommand(),
                new PositionCommand()
        );
    }


}
