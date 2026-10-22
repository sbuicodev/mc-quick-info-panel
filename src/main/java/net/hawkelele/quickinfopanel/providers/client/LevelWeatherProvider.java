package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.WeatherProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
//? if <1.21.11
//import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class LevelWeatherProvider implements WeatherProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public String getWeather() {
        assert client.level != null;
        assert client.player != null;


        BlockPos playerPosition = client.player.blockPosition();
        Biome biome = client.level.getBiome(playerPosition).value();
        //? if >=1.21.2
        int currentHeight = client.player.getBlockY();

        String weather = "unknown";
        //? if >=1.21.11 {
        if (client.level.canHaveWeather()) {
        //?} else {
        /*// Same check as Level.canHaveWeather(), which is private before 1.21.11
        if (client.level.dimensionType().hasSkyLight() && !client.level.dimensionType().hasCeiling() && client.level.dimension() != Level.END) {
        *///?}
            //? if >=1.21.2 {
            boolean isSnowing = biome.coldEnoughToSnow(playerPosition, currentHeight);
            //?} else {
            /*boolean isSnowing = biome.coldEnoughToSnow(playerPosition);
            *///?}
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
