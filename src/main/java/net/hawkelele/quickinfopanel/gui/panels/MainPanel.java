package net.hawkelele.quickinfopanel.gui.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.widgets.ClockWidget;
import net.hawkelele.quickinfopanel.gui.widgets.CompassWidget;
import net.hawkelele.quickinfopanel.gui.widgets.CoordinatesWidget;

public class MainPanel extends Layout {
    public MainPanel() {
        this.children(
                new CompassWidget(),
                new CoordinatesWidget(),
                new ClockWidget()
        );
    }

    @Override
    public boolean shouldBeHidden() {
        return !Config.read().displayMainPanel;
    }
}
