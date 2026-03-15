package net.hawkelele.quickinfopanel.gui.components;


import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.ClockService;

public class Clock extends Layout {
    public Clock() {
        this.children(
                new Text(ClockService.string()),
                new Image(ClockService.icon())
        ).gap(2);
    }
}
