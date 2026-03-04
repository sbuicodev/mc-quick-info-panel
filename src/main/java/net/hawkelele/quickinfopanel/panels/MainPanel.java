package net.hawkelele.quickinfopanel.panels;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.gui.properties.Alignment;
import net.hawkelele.quickinfopanel.gui.properties.Direction;
import net.hawkelele.quickinfopanel.panels.components.ClockPanel;
import net.hawkelele.quickinfopanel.panels.components.CompassPanel;
import net.hawkelele.quickinfopanel.panels.components.CoordinatesPanel;
import net.hawkelele.quickinfopanel.services.Coordinates;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

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
                .size(client.getWindow().getGuiScaledWidth(), -1)
                .align(Alignment.CENTER)
                .children(
                        new Panel()
                                .gap(2)
                                .direction(Direction.VERTICAL)
                                .reverse()
                                .children(
                                        new Panel()
                                                .gap(5)
                                                .align(Alignment.SPACE_BETWEEN)
                                                .children(
                                                        new CompassPanel(),
                                                        new CoordinatesPanel(),
                                                        new ClockPanel()
                                                )
                                                .size(160, -1)
                                )
                )
                .render(0, client.getWindow().getGuiScaledHeight() - (font.lineHeight * 7));

    }
}
