package net.hawkelele.quickinfopanel.elements;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.HashMap;

public class Weather {
    
    private final static HashMap<String, Component> icons = new HashMap<>() {{
        put("unknown", Component.literal("  "));
        put("clear", Component.literal("☀").withStyle(ChatFormatting.YELLOW));
        put("rain", Component.literal("\uD83D\uDCA7").withStyle(ChatFormatting.AQUA));
        put("thunder", Component.literal("⚡").withStyle(ChatFormatting.GOLD));
        put("thunder:snow", Component.literal("⚡").withStyle(ChatFormatting.GOLD));
        put("snow", Component.literal("❄").withStyle(ChatFormatting.WHITE));
    }};

    public static Component getIcon(String weather) {
        return icons.get(weather);
    }
}
