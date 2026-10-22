package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.providers.BiomeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.biome.Biome;

public class LevelBiomeProvider implements BiomeProvider {
    private static final Minecraft client = Minecraft.getInstance();

    public String getBiomeId() {
        assert client.level != null;
        assert client.player != null;

        BlockPos playerPosition = client.player.blockPosition();
        Holder<Biome> biomeHolder = client.level.getBiome(playerPosition);
        return biomeHolder.getRegisteredName();
    }

    @Override
    public String getBiome() {
        String id = getBiomeId();
        return Component.translatable("biomes." + Constants.MOD_ID + "." + id).getString();
    }
}
