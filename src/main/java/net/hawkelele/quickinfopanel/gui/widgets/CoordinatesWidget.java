package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.Coordinates;

public class CoordinatesWidget extends Layout {
    public CoordinatesWidget() {
        this.child(new Text(Coordinates.get().toString()));
    }
}
