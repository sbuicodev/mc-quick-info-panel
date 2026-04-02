package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.PlayerDimensionProvider;
import net.hawkelele.quickinfopanel.providers.client.PlayerPositionProvider;
import net.hawkelele.quickinfopanel.services.Coordinates;

public class CoordinatesWidget extends Layout {
    public CoordinatesWidget() {
        Coordinates coordinates = new Coordinates(new PlayerPositionProvider(), new PlayerDimensionProvider());
        this.child(new Text(Coordinates.toString(coordinates.getCurrentPosition())));
    }
}
