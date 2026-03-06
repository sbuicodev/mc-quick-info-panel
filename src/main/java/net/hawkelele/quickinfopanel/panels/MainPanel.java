package net.hawkelele.quickinfopanel.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.properties.Alignment;
import net.hawkelele.quickinfopanel.gui.properties.Justifying;
import net.hawkelele.quickinfopanel.gui.properties.Direction;
import net.hawkelele.quickinfopanel.panels.components.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class MainPanel extends Panel {
    private final Minecraft client = Minecraft.getInstance();
    private final Font font = client.font;

    @Override
    public boolean shouldBeHidden() {
        return client.options.hideGui
                || client.player == null
                || client.level == null
                || client.debugEntries.isOverlayVisible()
                || !Config.getInstance().settings().displayPanel
                || (hidden && Config.getInstance().settings().position.hideWithActionbar);
    }

    @Override
    public void render(int x, int y) {

        if (shouldBeHidden()) return;


        new Panel()
                .gap(2)
                .direction(Direction.VERTICAL)
                .align(Alignment.CENTER)
                .size(client.getWindow().getGuiScaledWidth(), -1)
                .reverse()
                .children(
                        new Panel()
                                .gap(5)
                                .justify(Justifying.SPACE_BETWEEN)
                                .children(
                                        new CompassPanel(),
                                        new CoordinatesPanel(),
                                        new ClockPanel()
                                )
                                .size(160, -1),
                        new Panel()
                                .gap(5)
                                .justify(Justifying.SPACE_BETWEEN)
                                .children(
                                        new OppositeCoordinatesPanel(),
                                        new BiomeAndWeatherPanel()
                                ).size(160, -1)
                ).render(0, client.getWindow().getGuiScaledHeight() - (font.lineHeight * 7));

    }
}
