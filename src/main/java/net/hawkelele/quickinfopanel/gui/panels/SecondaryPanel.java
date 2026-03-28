package net.hawkelele.quickinfopanel.gui.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.components.BiomeAndWeather;
import net.hawkelele.quickinfopanel.gui.components.OppositeCoordinates;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;

public class SecondaryPanel extends Layout {
    public SecondaryPanel() {
        this.children(
                new OppositeCoordinates(),
                new BiomeAndWeather()
        );
    }

    @Override
    public boolean shouldBeHidden() {
        return !Config.getInstance().settings().displaySecondaryPanel;
    }
}
