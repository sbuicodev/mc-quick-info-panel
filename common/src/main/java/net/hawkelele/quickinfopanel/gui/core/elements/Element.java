package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public abstract class Element {
    protected boolean hidden = false;

    public boolean shouldBeHidden() {
        return hidden;
    }

    public void hide() {
        this.hidden = true;
    }

    public void show() {
        if (this.shouldBeHidden()) return;

        this.hidden = false;
    }

    abstract int getWidth();
    abstract int getHeight();

    public abstract void render(int x, int y);

    protected void renderDebugBounds(int x, int y, int color) {
        if (!Config.INSTANCE.debugBounds) return;

        GuiGraphicsExtractor context = Graphics.get();
        int w = getWidth();
        int h = getHeight();

        context.outline(x, y, x + w, y + h, color);

        String label = w + "x" + h;
        int labelWidth = Minecraft.getInstance().font.width(label);
        context.fill(x + 1, y + 1, x + 1 + labelWidth + 1, y + 1 + Minecraft.getInstance().font.lineHeight, 0xA0000000);
        context.text(Minecraft.getInstance().font, label, x + 1, y + 1, color);
    }

}
