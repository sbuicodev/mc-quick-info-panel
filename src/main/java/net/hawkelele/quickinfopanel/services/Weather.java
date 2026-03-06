package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;

public class Weather {
    private static final Minecraft client = Minecraft.getInstance();
    private static final Level level = client.level;
    private static final Player player = client.player;


    private final static HashMap<String, Component> icons = new HashMap<>() {{
        put("unknown", Component.literal("  "));
        put("clear", Component.literal("☀").withStyle(ChatFormatting.YELLOW));
        put("rain", Component.literal("\uD83D\uDCA7").withStyle(ChatFormatting.AQUA));
        put("thunder", Component.literal("⚡").withStyle(ChatFormatting.GOLD));
        put("thunder:snow", Component.literal("⚡").withStyle(ChatFormatting.GOLD));
        put("snow", Component.literal("❄").withStyle(ChatFormatting.WHITE));
    }};

    public static BlockPos getPlayerPosition() {
        assert player != null;
        return player.blockPosition();
    }

    public static Biome getBiome() {
        assert level != null;
        Holder<Biome> biomeHolder = level.getBiome(getPlayerPosition());
        return biomeHolder.value();
    }

    public static String getBiomeId() {
        assert level != null;
        Holder<Biome> biomeHolder = level.getBiome(getPlayerPosition());
        return biomeHolder.getRegisteredName();
    }

    public static String getBiomeName() {
        String id = getBiomeId();
        return Component.translatable("biomes." + QuickInfoPanel.MOD_ID + "." + id).getString();
    }

    public static String getCurrent() {
        assert level != null;
        assert player != null;

        BlockPos blockPos = getPlayerPosition();
        Biome biome = getBiome();
        int currentHeight = player.getBlockY();

        String weather = "unknown";
        if (level.canHaveWeather()) {
            boolean isSnowing = biome.coldEnoughToSnow(blockPos, currentHeight);
            if (level.isThundering()) {
                weather = isSnowing ? "thunder:snow" : "thunder";
            } else if (level.isRaining()) {
                weather = isSnowing ? "snow" : "rain";
            } else {
                weather = "clear";
            }
        }

        return weather;
    }

    public static Component getIcon() {
        return icons.getOrDefault(getCurrent(), Component.empty());
    }
}
