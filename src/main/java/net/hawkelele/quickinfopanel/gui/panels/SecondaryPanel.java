package net.hawkelele.quickinfopanel.gui.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.widgets.BiomeAndWeatherWidget;
import net.hawkelele.quickinfopanel.gui.widgets.OppositeCoordinatesWidget;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;

public class SecondaryPanel extends Layout {
    public SecondaryPanel() {
        this.children(
                new OppositeCoordinatesWidget(),
                new BiomeAndWeatherWidget()
        );
    }

    @Override
    public boolean shouldBeHidden() {
        return !Config.getInstance().settings().displaySecondaryPanel;
    }
}
