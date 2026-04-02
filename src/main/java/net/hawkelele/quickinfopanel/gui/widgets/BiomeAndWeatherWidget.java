package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.Weather;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class BiomeAndWeatherWidget extends Layout {
    public BiomeAndWeatherWidget() {
        this.children(
                new Text(Component.literal(Weather.getBiomeName()).withStyle(ChatFormatting.GRAY)),
                new Text(Weather.getIcon())
        ).gap(2);
    }
}
