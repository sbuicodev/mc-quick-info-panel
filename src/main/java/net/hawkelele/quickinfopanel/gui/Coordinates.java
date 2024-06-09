package net.hawkelele.quickinfopanel.gui;

import net.minecraft.client.MinecraftClient;

public class Coordinates {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public int x;
    public int y;
    public int z;

    private Coordinates(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private static double[] fetchRawCoordinates() {
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

    public static Coordinates getForAlternateDimension() {
        double[] coordinates = fetchRawCoordinates();

        double scaleFactor = 0.125; // Overworld -> Nether
        if (client.world.getDimensionEntry().getIdAsString().equals("minecraft:the_nether")) {
            scaleFactor = 8; // Nether -> Overworld
        }
        return new Coordinates(
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        );
    }

}
