package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.providers.BiomeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.biome.Biome;

public class LevelBiomeProvider implements BiomeProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public String getBiomeId() {
        assert client.level != null;
        assert client.player != null;

        BlockPos playerPosition = client.player.blockPosition();
        Holder<Biome> biomeHolder = client.level.getBiome(playerPosition);
        //? if >=1.20.5 {
        return biomeHolder.getRegisteredName();
        //?} else {
        /*return biomeHolder.unwrapKey().map(key -> key.location().toString()).orElse("[unregistered]");
        *///?}
    }

    @Override
    public String getTranslation(String biomeId) {
        String key = "biomes." + Constants.MOD_ID + "." + biomeId;
        return Language.getInstance().has(key) ? Component.translatable(key).getString() : null;
    }
}
