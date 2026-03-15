package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.hawkelele.quickinfopanel.gui.core.Context;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;

import static net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED;


public class Image extends Element {
    protected final Minecraft client = Minecraft.getInstance();
    protected final Font font = client.font;
    protected final GuiGraphics context = Context.get();

    protected int width = font.lineHeight;
    protected int height = font.lineHeight;

    protected String path;

    public Image(String path) {
        this.path = path;
    }

    @Override
    int getWidth() {
        return width;
    }

    @Override
    int getHeight() {
        return height;
    }

    @Override
    void render(int x, int y) {
        context.blit(GUI_TEXTURED, Identifier.fromNamespaceAndPath(QuickInfoPanel.MOD_ID, path), x, y, 0, 0, width, height, width, height);
    }
}
