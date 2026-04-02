package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.PlayerDirectionProvider;
import net.hawkelele.quickinfopanel.services.Compass;

public class CompassWidget extends Layout {
    public CompassWidget() {
        Compass compass = new Compass(new PlayerDirectionProvider());
        this.children(
                new Image(compass.getIconPath()),
                new Text(compass.getCurrentFacingCardinalDirection())
        ).gap(2);
    }
}
