package net.hawkelele.quickinfopanel.gui.core;

import net.minecraft.client.gui.GuiGraphics;

public class Context {
    private static GuiGraphics context;

    public static void set(GuiGraphics context) {
        Context.context = context;
    }

    public static GuiGraphics get() {
        return context;
    }

}
