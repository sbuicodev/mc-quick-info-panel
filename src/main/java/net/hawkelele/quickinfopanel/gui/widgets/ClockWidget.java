package net.hawkelele.quickinfopanel.gui.widgets;


import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.minecraft.InGameTimeProvider;

public class ClockWidget extends Layout {
    public ClockWidget() {
        net.hawkelele.quickinfopanel.services.Clock clock = new net.hawkelele.quickinfopanel.services.Clock(new InGameTimeProvider());
        this.children(
                new Text(clock.getCurrentTimeAsClockString()),
                new Image(clock.icon())
        ).gap(2);
    }
}
