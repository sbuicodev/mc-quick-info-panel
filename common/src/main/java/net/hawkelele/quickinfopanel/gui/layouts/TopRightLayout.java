package net.hawkelele.quickinfopanel.gui.layouts;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.properties.Direction;
import net.hawkelele.quickinfopanel.gui.panels.MainPanel;
import net.hawkelele.quickinfopanel.gui.panels.SecondaryPanel;
import net.minecraft.client.Minecraft;

public class TopRightLayout extends Layout {
    private final Minecraft client = Minecraft.getInstance();

    @Override
    public void render(int x, int y) {
        Layout stack = new Layout()
                .gap(2)
                .direction(Direction.VERTICAL)
                .children(
                        new MainPanel().gap(5),
                        new SecondaryPanel().gap(5)
                );

        stack.render(client.getWindow().getGuiScaledWidth() - stack.getWidth() - 5, 5);
    }
}
