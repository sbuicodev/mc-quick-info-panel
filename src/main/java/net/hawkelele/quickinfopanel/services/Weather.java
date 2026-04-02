package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;

public class Weather {
    private static final Minecraft client = Minecraft.getInstance();


    private final static HashMap<String, Component> icons = new HashMap<>() {{
        put("unknown", Component.literal("  "));
        put("clear", Component.literal("☀").withStyle(ChatFormatting.YELLOW));
        put("rain", Component.literal("☔").withStyle(ChatFormatting.AQUA));
        put("thunder", Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("thunder:snow", Component.literal("⚡").withStyle(ChatFormatting.YELLOW));
        put("snow", Component.literal("❄").withStyle(ChatFormatting.WHITE));
    }};

    public static BlockPos getPlayerPosition() {
        assert client.player != null;
        return client.player.blockPosition();
    }

    public static Biome getBiome() {
        assert client.level != null;
        Holder<Biome> biomeHolder = client.level.getBiome(getPlayerPosition());
        return biomeHolder.value();
    }

    public static String getBiomeId() {
        assert client.level != null;
        Holder<Biome> biomeHolder = client.level.getBiome(getPlayerPosition());
        return biomeHolder.getRegisteredName();
    }

    public static String getBiomeName() {
        String id = getBiomeId();
        return Component.translatable("biomes." + QuickInfoPanel.MOD_ID + "." + id).getString();
    }

    public static String getCurrent() {
        assert client.level != null;
        assert client.player != null;

        BlockPos blockPos = getPlayerPosition();
        Biome biome = getBiome();
        int currentHeight = client.player.getBlockY();

        String weather = "unknown";
        if (client.level.canHaveWeather()) {
            boolean isSnowing = biome.coldEnoughToSnow(blockPos, currentHeight);
            if (client.level.isThundering()) {
                weather = isSnowing ? "thunder:snow" : "thunder";
            } else if (client.level.isRaining()) {
                weather = isSnowing ? "snow" : "rain";
            } else {
                weather = "clear";
            }
        }

        return weather;
    }

    public static Component getIcon(String weather) {
        return icons.getOrDefault(weather, Component.empty());
    }

    public static Component getIcon() {
        return getIcon(getCurrent());
    }
}
