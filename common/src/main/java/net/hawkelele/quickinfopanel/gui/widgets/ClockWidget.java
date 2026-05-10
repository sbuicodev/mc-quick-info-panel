package net.hawkelele.quickinfopanel.gui.widgets;


import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.InGameTimeProvider;

public class ClockWidget extends Layout {
    @Override
    public boolean shouldBeHidden() {
        return !Config.read().panels.getOrDefault("clock", true);
    }

    public ClockWidget() {
        net.hawkelele.quickinfopanel.services.Clock clock = new net.hawkelele.quickinfopanel.services.Clock(new InGameTimeProvider());
        this.children(
                new Text(clock.getCurrentTimeAsClockString()),
                new Image(clock.getIconPath())
        ).gap(2);
    }
}
