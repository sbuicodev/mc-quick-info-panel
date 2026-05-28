package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.WeatherProvider;

import java.util.Arrays;

public class Weather {

    private final WeatherProvider weatherProvider;

    public Weather(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public String getCurrent() {
        String[] allowed = new String[]{
                "unknown", "clear", "rain", "thunder", "thunder:snow", "snow"
        };

        String weather = weatherProvider.getWeather();

        if (weather == null || !Arrays.asList(allowed).contains(weather)) {
            return "unknown";
        }

        return weather;
    }
}
