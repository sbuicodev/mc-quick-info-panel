package net.hawkelele.quickinfopanel.gui;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.hawkelele.quickinfopanel.gui.core.Context;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.layouts.RootLayout;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jspecify.annotations.NonNull;

public class Panel implements HudElement {
    private static final Minecraft client = Minecraft.getInstance();
    private int timer = -1;
    private boolean hidden = false;

    public void hideFor(int ticks) {
        timer = ticks;
        hidden = true;
    }

    public void tick() {
        if (client.isPaused()) return;

        if (timer > 0) {
            timer--;
        } else {
            hidden = false;
        }
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, @NonNull DeltaTracker deltaTracker) {

        if (hidden) {
            return;
        }
        Context.set(graphics);
        Layout panel = new RootLayout();
        panel.render();
    }
}
