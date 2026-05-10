package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.providers.client.OverlayMessageStatusProvider;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.client.Minecraft;

public class RootLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();

    @Override
    public boolean shouldBeHidden() {
        return client.options.hideGui
                || client.player == null
                || client.level == null
                || client.debugEntries.isOverlayVisible()
                || OverlayMessageStatusProvider.isDisplaying();
    }

    @Override
    public void render(int x, int y) {
        if (hidden || shouldBeHidden()) return;
        Layout currentLayout = LayoutRegistry.get("default");
        currentLayout.render(x, y);
    }
}
