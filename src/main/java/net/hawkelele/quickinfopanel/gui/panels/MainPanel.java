package net.hawkelele.quickinfopanel.gui.panels;

import net.hawkelele.quickinfopanel.gui.components.Clock;
import net.hawkelele.quickinfopanel.gui.components.Compass;
import net.hawkelele.quickinfopanel.gui.components.Coordinates;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;

public class MainPanel extends Layout {
    public MainPanel() {
        this.children(
                new Compass(),
                new Coordinates(),
                new Clock()
        );
    }
}
