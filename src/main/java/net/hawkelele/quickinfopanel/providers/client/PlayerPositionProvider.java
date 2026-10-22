package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.PositionProvider;
import net.minecraft.client.Minecraft;

public class PlayerPositionProvider implements PositionProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public double[] getPosition() {
        assert client.player != null;

        return new double[]{
                client.player.getX(),
                client.player.getY(),
                client.player.getZ()
        };
    }

}
