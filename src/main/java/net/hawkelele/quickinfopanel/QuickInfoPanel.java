package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.providers.PanelHidingOnGameMessageProvider;
import net.hawkelele.quickinfopanel.providers.PanelHudRenderCallbackProvider;
import net.hawkelele.quickinfopanel.providers.PanelToggleKeyListener;
import net.hawkelele.quickinfopanel.commands.NetherCommand;
import net.hawkelele.quickinfopanel.gui.mixins.PanelHudMixin;
import net.hawkelele.quickinfopanel.registries.CommandsRegistry;
import net.hawkelele.quickinfopanel.registries.ProvidersRegistry;


public class QuickInfoPanel implements ClientModInitializer {
    public static final PanelHudMixin PANEL = new PanelHudMixin();

    @Override
    public void onInitializeClient() {
        ProvidersRegistry.register(
                new PanelHudRenderCallbackProvider(),
                new PanelHidingOnGameMessageProvider(),
                new PanelToggleKeyListener()
        );

        CommandsRegistry.register(
                new NetherCommand()
        );
    }



}
