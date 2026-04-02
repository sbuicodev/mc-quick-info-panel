package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.WeatherProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;

public class LevelWeatherProvider implements WeatherProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public String getWeather() {
        assert client.level != null;
        assert client.player != null;


        BlockPos playerPosition = client.player.blockPosition();
        Biome biome = client.level.getBiome(playerPosition).value();
        int currentHeight = client.player.getBlockY();

        String weather = "unknown";
        if (client.level.canHaveWeather()) {
            boolean isSnowing = biome.coldEnoughToSnow(playerPosition, currentHeight);
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
}
