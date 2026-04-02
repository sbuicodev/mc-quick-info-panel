package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.DirectionProvider;
import net.minecraft.client.Minecraft;

public class PlayerDirectionProvider implements DirectionProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public String getDirection() {
        assert client.player != null;

        return client.player.getDirection().toString();
    }
}
