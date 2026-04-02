package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.Compass;

public class CompassWidget extends Layout {
    public CompassWidget() {
        this.children(
                new Image(Compass.icon()),
                new Text(Compass.string())
        ).gap(2);
    }
}
