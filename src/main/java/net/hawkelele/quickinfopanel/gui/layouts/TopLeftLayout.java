package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.gui.components.*;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.properties.Alignment;
import net.hawkelele.quickinfopanel.gui.core.properties.Direction;
import net.hawkelele.quickinfopanel.gui.core.properties.Justifying;
import net.hawkelele.quickinfopanel.gui.panels.MainPanel;
import net.hawkelele.quickinfopanel.gui.panels.SecondaryPanel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class TopLeftLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();
    private final Font font = client.font;

    @Override
    public void render(int x, int y) {
        new Layout()
                .gap(2)
                .direction(Direction.VERTICAL)
                .children(
                        new MainPanel()
                                .gap(5),
                        new SecondaryPanel()
                                .gap(5)
                ).render(5, 5);
    }
}
