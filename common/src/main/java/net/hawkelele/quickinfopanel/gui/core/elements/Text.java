package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.Common;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class Text extends Element {
    private final Component text;

    public Text(Component text) {
        this.text = text;
    }

    public Text(String text) {
        this(Component.literal(text));
    }

    @Override
    public int getWidth() {
        Font font = Minecraft.getInstance().font;
        return font.width(text);
    }

    @Override
    public int getHeight() {
        Font font = Minecraft.getInstance().font;
        return font.lineHeight;
    }

    @Override
    public void render(int x, int y) {
        GuiGraphicsExtractor context = Graphics.get();
        Font font = Minecraft.getInstance().font;
        context.text(font, text, x, y, CommonColors.WHITE);
        renderDebugBounds(x, y, 0xFF44FF44);
    }
}
