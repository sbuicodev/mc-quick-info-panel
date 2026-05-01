package net.hawkelele.quickinfopanel.gui.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.widgets.BiomeAndWeatherWidget;
import net.hawkelele.quickinfopanel.gui.widgets.OppositeCoordinatesWidget;

public class SecondaryPanel extends Layout {
    public SecondaryPanel() {
        this.children(
                new OppositeCoordinatesWidget(),
                new BiomeAndWeatherWidget()
        );
    }

    @Override
    public boolean shouldBeHidden() {
        return false;
//        return !Config.getInstance().settings().displaySecondaryPanel;
    }
}
