package net.hawkelele.quickinfopanel.gui.components;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.CoordinatesService;

public class Coordinates extends Layout {
    public Coordinates() {
        this.child(new Text(CoordinatesService.get().toString()));
    }
}
