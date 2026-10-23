package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

//? if >=1.21.6 {
import static net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED;
//?} else if >=1.21.2 {
/*import net.minecraft.client.renderer.RenderType;
*///?}


public class Image extends Element {
    Font font = Minecraft.getInstance().font;

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
    public void render(int x, int y) {
        GuiGraphicsExtractor context = Graphics.get();
        //? if >=1.21.6 {
        context.blit(GUI_TEXTURED, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path), x, y, 0, 0, width, height, width, height);
        //?} else if >=1.21.2 {
        /*context.blit(RenderType::guiTextured, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path), x, y, 0, 0, width, height, width, height);
        *///?} else if >=1.21 {
        /*context.blit(Identifier.fromNamespaceAndPath(Constants.MOD_ID, path), x, y, 0, 0, width, height, width, height);
        *///?} else {
        /*context.blit(new Identifier(Constants.MOD_ID, path), x, y, 0, 0, width, height, width, height);
        *///?}
        renderDebugBounds(x, y, 0xFF4488FF);
    }
}
