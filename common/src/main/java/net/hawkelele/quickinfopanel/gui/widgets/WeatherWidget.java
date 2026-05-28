package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.WeatherIcons;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.LevelWeatherProvider;
import net.hawkelele.quickinfopanel.services.Weather;

public class WeatherWidget extends Layout {
    @Override
    public boolean shouldBeHidden() {
        return !Config.read().panels.getOrDefault("weather", true);
    }

    public WeatherWidget() {
        Weather weather = new Weather(new LevelWeatherProvider());
        this.children(new Text(WeatherIcons.get(weather.getCurrent())));
    }
}
