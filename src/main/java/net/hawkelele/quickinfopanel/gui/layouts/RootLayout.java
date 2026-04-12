package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.providers.client.OverlayMessageStatusProvider;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class RootLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();
    private final Font font = client.font;

    @Override
    public boolean shouldBeHidden() {
        return client.options.hideGui
                || client.player == null
                || client.level == null
                || client.debugEntries.isOverlayVisible()
                || OverlayMessageStatusProvider.isDisplaying()
                || !Config.getInstance().settings().displayMainPanel;
    }

    @Override
    public void render(int x, int y) {
        if (hidden || shouldBeHidden()) return;
        Layout currentLayout = LayoutRegistry.get(Config.getInstance().settings().layout);
        currentLayout.render(x, y);
    }
}
