package net.hawkelele.quickinfopanel.gui.core;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.HashMap;

public class WeatherIcons {
    private static final HashMap<String, Component> ICONS = new HashMap<>() {{
        put("unknown",      Component.literal("  "));
        put("clear",        Component.literal("☀").withStyle(ChatFormatting.YELLOW));
        put("rain",         Component.literal("☔").withStyle(ChatFormatting.AQUA));
        put("thunder",      Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("thunder:snow", Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("snow",         Component.literal("❄").withStyle(ChatFormatting.WHITE));
    }};

    public static Component get(String weather) {
        return ICONS.getOrDefault(weather, Component.empty());
    }
}
