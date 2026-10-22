package net.hawkelele.quickinfopanel.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Graphics {
    private static GuiGraphicsExtractor graphics;

    public static void register(GuiGraphicsExtractor graphics) {
        Graphics.graphics = graphics;
    }

    public static GuiGraphicsExtractor get() throws RuntimeException {
        if (graphics == null) {
            throw new RuntimeException("Couldn't get graphics extractor before initialization");
        }

        return Graphics.graphics;
    }
}
