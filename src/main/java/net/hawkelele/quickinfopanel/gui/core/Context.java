package net.hawkelele.quickinfopanel.gui.core;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Context {
    private static GuiGraphicsExtractor graphics;

    public static void set(GuiGraphicsExtractor context) {
        Context.graphics = context;
    }

    public static GuiGraphicsExtractor get() {
        return graphics;
    }

}
