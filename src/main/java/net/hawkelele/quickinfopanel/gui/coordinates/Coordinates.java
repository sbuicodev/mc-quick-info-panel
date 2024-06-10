package net.hawkelele.quickinfopanel.gui.coordinates;

import net.minecraft.client.MinecraftClient;

public class Coordinates {
    protected static final MinecraftClient client = MinecraftClient.getInstance();

    public final int x;
    public final int y;
    public final int z;

    protected Coordinates(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected static double[] fetchRawCoordinates() {
        assert client.player != null;
        return new double[]{
                client.player.getX(),
                client.player.getY(),
                client.player.getZ()
        };
    }

    public static Coordinates get() {
        double[] coordinates = fetchRawCoordinates();
        return new Coordinates(
                (int) Math.floor(coordinates[0]),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2])
        );
    }
}
