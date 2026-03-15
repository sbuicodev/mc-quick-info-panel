package net.hawkelele.quickinfopanel.panels.components;

import net.hawkelele.quickinfopanel.gui.elements.Image;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.services.Weather;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class BiomeAndWeatherPanel extends Panel {
    public BiomeAndWeatherPanel() {
        this.children(
                new Text(Component.literal(Weather.getBiomeName()).withStyle(ChatFormatting.GRAY)),
                new Text(Weather.getIcon())
        ).gap(2);
    }
}
