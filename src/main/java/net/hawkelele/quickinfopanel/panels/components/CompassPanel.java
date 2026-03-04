package net.hawkelele.quickinfopanel.panels.components;

import net.hawkelele.quickinfopanel.gui.elements.Image;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.services.Compass;

public class CompassPanel extends Panel {
    public CompassPanel() {
        this.children(
                new Image(Compass.icon()),
                new Text(Compass.string())
        ).gap(2);
    }
}
