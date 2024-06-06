package net.hawkelele.quickinfopanel.providers;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.hawkelele.quickinfopanel.QuickInfoPanel;

public class PanelHudRenderCallbackProvider implements ProviderInterface {

    public void register() {
        // On each in-game HUD render, the panel gets rendered with it
        HudRenderCallback.EVENT.register(QuickInfoPanel.PANEL);
    }
}
