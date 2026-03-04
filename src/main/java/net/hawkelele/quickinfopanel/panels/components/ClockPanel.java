package net.hawkelele.quickinfopanel.panels.components;


import net.hawkelele.quickinfopanel.gui.elements.Image;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.services.Clock;

public class ClockPanel extends Panel {
    public ClockPanel() {
        this.children(
                new Text(Clock.string()),
                new Image(Clock.icon())
        ).gap(2);
    }
}
