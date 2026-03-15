package net.hawkelele.quickinfopanel.gui.components;

import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.CompassService;

public class Compass extends Layout {
    public Compass() {
        this.children(
                new Image(CompassService.icon()),
                new Text(CompassService.string())
        ).gap(2);
    }
}
