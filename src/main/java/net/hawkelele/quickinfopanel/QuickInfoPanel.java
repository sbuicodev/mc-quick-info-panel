package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.command.NetherCommand;
import net.hawkelele.quickinfopanel.command.PositionCommand;
import net.hawkelele.quickinfopanel.registry.Commands;
import net.hawkelele.quickinfopanel.registry.Services;
import net.hawkelele.quickinfopanel.event.TogglePanelOnKeypress;


public class QuickInfoPanel implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Services.register(
                new TogglePanelOnKeypress()
        );

        Commands.register(
                new NetherCommand(),
                new PositionCommand()
        );
    }
}
