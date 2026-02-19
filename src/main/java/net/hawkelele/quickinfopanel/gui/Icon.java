package net.hawkelele.quickinfopanel.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

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

    public void offsetX(int x) {
        this.x += x;
    }

    public void draw(GuiGraphics context) {
        context.blit(RenderPipelines.GUI_TEXTURED, Identifier.fromNamespaceAndPath(QuickInfoPanel.MOD_ID, path), x, y, 0, 0, width, height, width, height);
    }
}
