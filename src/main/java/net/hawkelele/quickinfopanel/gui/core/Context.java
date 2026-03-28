package net.hawkelele.quickinfopanel.gui.core;

import net.minecraft.client.gui.GuiGraphicsExtractor;

public class Context {
    private static GuiGraphicsExtractor context;

    public static void set(GuiGraphicsExtractor context) {
        Context.context = context;
    }

    public static GuiGraphicsExtractor get() {
        return context;
    }

}
