package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.WeatherProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.HashMap;

public class Weather {

    private final static HashMap<String, Component> icons = new HashMap<>() {{
        put("unknown", Component.literal("  "));
        put("clear", Component.literal("☀").withStyle(ChatFormatting.YELLOW));
        put("rain", Component.literal("☔").withStyle(ChatFormatting.AQUA));
        put("thunder", Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("thunder:snow", Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("snow", Component.literal("❄").withStyle(ChatFormatting.WHITE));
    }};

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

    public static Component getIcon(String weather) {
        return icons.getOrDefault(weather, Component.empty());
    }

    public Component getIcon() {
        return getIcon(getCurrent());
    }
}
