package net.hawkelele.quickinfopanel.panels.components;

import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.services.Coordinates;

public class CoordinatesPanel extends Panel {
    public CoordinatesPanel() {
        this.child(new Text(Coordinates.get().toString()));
    }
}
