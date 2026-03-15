package net.hawkelele.quickinfopanel.gui.components;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.WeatherService;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class BiomeAndWeather extends Layout {
    public BiomeAndWeather() {
        this.children(
                new Text(Component.literal(WeatherService.getBiomeName()).withStyle(ChatFormatting.GRAY)),
                new Text(WeatherService.getIcon())
        ).gap(2);
    }
}
