package net.hawkelele.quickinfopanel.gui.core.elements;

import net.hawkelele.quickinfopanel.gui.core.Context;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class Text extends Element {
    private final Font font = Minecraft.getInstance().font;
    private final GuiGraphics context = Context.get();

    private final Component text;

    public Text(Component text) {
        this.text = text;
    }

    public Text(String text) {
        this(Component.literal(text));
    }

    @Override
    public int getWidth() {
        return font.width(text);
    }

    @Override
    public int getHeight() {
        return font.lineHeight;
    }

    @Override
    public void render(int x, int y) {
        context.drawString(font, text, x, y, CommonColors.WHITE);
    }
}
