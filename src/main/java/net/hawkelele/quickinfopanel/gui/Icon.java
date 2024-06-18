package net.hawkelele.quickinfopanel.gui;

import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class Icon {
    private final String path;
    private int x;
    private final int y;
    private final int width;
    private final int height;

    private Icon(String path, int x, int y, int width, int height) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static Icon of(String path, int x, int y) {
        return new Icon(path, x, y - 3, 12, 12);
    }

    public int getWidth() {
        return width;
    }

    public void offsetX(int x) {
        this.x += x;
    }

    public void draw(DrawContext context) {
        context.drawTexture(Identifier.of(QuickInfoPanel.MOD_ID, path), x, y, width, height, width, height, width, height);
    }
}
