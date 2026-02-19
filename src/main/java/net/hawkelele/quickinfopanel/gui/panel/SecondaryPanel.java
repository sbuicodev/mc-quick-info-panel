package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.Utils;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.elements.Weather;
import net.hawkelele.quickinfopanel.gui.coordinates.AlternateDimensionCoordinates;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.IOException;

public class SecondaryPanel extends Panel {
    private static SecondaryPanel instance;

    public static SecondaryPanel getInstance() {
        return instance == null ? instance = new SecondaryPanel() : instance;
    }

    public Component getAlternateDimensionIcon() {
        assert client.level != null;
        String dimension = client.level.dimensionTypeRegistration().getRegisteredName();
        return switch (dimension) {
            case "minecraft:the_nether" -> Component.literal("\uD83C\uDF33").withStyle(ChatFormatting.DARK_GREEN);
            case "minecraft:the_end" -> Component.empty();
            default -> Component.literal("\uD83D\uDD25").withStyle(ChatFormatting.GOLD);
        };
    }

    @Override
    public int[] getTextPosition(int width) {
        int[] position = super.getTextPosition(width);

        int lineHeight = 12;
        if (!Config.getInstance().settings().position.invertLines) {
            lineHeight = -lineHeight;
        }

        if (Config.getInstance().settings().position.centered) {
            position[0] -= 10;
        } else {
            position[0] += 2;
        }

        position[1] += lineHeight;

        return position;

    }

    public String getWeather(Level level, Biome biome, BlockPos blockPos, int currentHeight) {
        if (!level.canHaveWeather())
            return "unknown";

        boolean isSnowing = biome.coldEnoughToSnow(blockPos, currentHeight);

        if (level.isThundering())
            return isSnowing ? "thunder:snow" : "thunder";
        if (level.isRaining())
            return isSnowing ? "snow" : "rain";

        return "clear";
    }

    public void drawAlternateDimension(GuiGraphics context) {
        Coordinates coordinates = AlternateDimensionCoordinates.get();

        Component text = Component.empty()
                .append(getAlternateDimensionIcon())
                .append(" ")
                .append(Component.literal("XZ: ").withStyle(ChatFormatting.YELLOW))
                .append(Component.literal(String.valueOf(coordinates.x)).withColor(Color.decode("#BBBBBB").hashCode()))
                .append(" ")
                .append(Component.literal(String.valueOf(coordinates.z)).withColor(Color.decode("#BBBBBB").hashCode()));

        int[] position = getTextPosition(client.font.width(text) + 80);

        context.drawString(
                client.font,
                text,
                position[0], position[1],
                Color.decode("#AAAAAA").hashCode(),
                true);

    }

    public void drawBiomeAndWeather(GuiGraphics context) {
        assert client.player != null;
        assert client.level != null;

        try (Level level = client.player.level()) {

            BlockPos blockPos = client.player.blockPosition();
            Holder<Biome> biomeHolder = level.getBiome(blockPos);
            Biome biome = biomeHolder.value();
            String biomeName = biomeHolder.getRegisteredName();

            String weather = getWeather(level, biome, blockPos, client.player.getBlockY());

            String translatedWeather = Utils.truncate(
                    Component.translatable("biomes.quickinfopanel." + biomeName).getString(),
                    16,
                    5);

            String dynamicEmptySpace = StringUtils.repeat(' ',
                    22 - Weather.getIcon(weather).getString().length() - translatedWeather.length());

            Component text = Component.empty()
                    .append(dynamicEmptySpace)
                    .append(translatedWeather)
                    .append(" ")
                    .append(Weather.getIcon(weather));

            int[] position = getTextPosition(client.font.width(text) - 90);

            context.drawString(
                    client.font,
                    text,
                    position[0], position[1],
                    Color.decode("#AAAAAA").hashCode(),
                    true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override

    public void draw(GuiGraphics context) {
        drawAlternateDimension(context);
        drawBiomeAndWeather(context);
    }

    @Override
    public boolean shouldBeHidden() {
        return super.shouldBeHidden()
                || client.level == null
                || !Config.getInstance().settings().displayAlternateDimensionInfo
                || client.level.dimensionTypeRegistration().getRegisteredName().equals("the_end");
    }
}
