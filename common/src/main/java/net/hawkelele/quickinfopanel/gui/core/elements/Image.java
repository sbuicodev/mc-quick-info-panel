package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

import static net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED;


public class Image extends Element {
    protected int width;
    protected int height;

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
    public void render(int x, int y) {
        GuiGraphicsExtractor context = Graphics.get();
        Font font = Minecraft.getInstance().font;
        width = font.lineHeight;
        height = font.lineHeight;
        context.blit(GUI_TEXTURED, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path), x, y, 0, 0, width, height, width, height);
    }
}
