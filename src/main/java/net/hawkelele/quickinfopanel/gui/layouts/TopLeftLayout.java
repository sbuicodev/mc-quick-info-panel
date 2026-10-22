package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.properties.Direction;
import net.hawkelele.quickinfopanel.gui.panels.MainPanel;
import net.hawkelele.quickinfopanel.gui.panels.SecondaryPanel;

public class TopLeftLayout extends Layout {
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
