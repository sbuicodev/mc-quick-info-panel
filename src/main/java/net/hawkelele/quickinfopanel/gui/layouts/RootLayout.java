package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.providers.client.OverlayMessageStatusProvider;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.client.Minecraft;

public class RootLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();

    @Override
    public boolean shouldBeHidden() {
        //? if >=26.2 {
        return client.gui.hud.isHidden()
        //?} else {
        /*return client.options.hideGui
        *///?}
                || client.player == null
                || client.level == null
                //? if >=1.21.11 {
                || client.debugEntries.isOverlayVisible()
                //?} else {
                /*|| client.getDebugOverlay().showDebugScreen()
                *///?}
                || OverlayMessageStatusProvider.isDisplaying();
    }

    @Override
    public void render(int x, int y) {
        if (hidden || shouldBeHidden()) return;
        Layout currentLayout = LayoutRegistry.get(Config.read().layout);
        currentLayout.render(x, y);
    }
}
