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

public class DefaultLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();
    private final Font font = client.font;

    @Override
    public void render(int x, int y) {
        new Layout()
                .gap(2)
                .direction(Direction.VERTICAL)
                .align(Alignment.CENTER)
                .size(client.getWindow().getGuiScaledWidth(), -1)
                .reverse()
                .children(
                        new MainPanel()
                                .gap(5)
                                .justify(Justifying.SPACE_BETWEEN)
                                .size(160, -1),
                        new SecondaryPanel()
                                .gap(5)
                                .justify(Justifying.SPACE_BETWEEN)
                                .size(160, -1)
                )
                .render(0, client.getWindow().getGuiScaledHeight() - (font.lineHeight * 8));
    }
}
