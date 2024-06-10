package net.hawkelele.quickinfopanel.gui.coordinates;

import net.minecraft.client.MinecraftClient;

public class AlternateDimensionCoordinates extends Coordinates {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public int x;
    public int y;
    public int z;

    protected AlternateDimensionCoordinates(int x, int y, int z) {
        super(x, y, z);
    }

    public static AlternateDimensionCoordinates get() {
        double[] coordinates = fetchRawCoordinates();

        double scaleFactor = 0.125; // Overworld -> Nether
        if (client.world.getDimensionEntry().getIdAsString().equals("minecraft:the_nether")) {
            scaleFactor = 8; // Nether -> Overworld
        }
        return new AlternateDimensionCoordinates(
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        );
    }

}
