package net.hawkelele.quickinfopanel.legacy.gui.coordinates;

import net.minecraft.client.Minecraft;

public class AlternateDimensionCoordinates extends Coordinates {
    private static final Minecraft client = Minecraft.getInstance();

    public int x;
    public int y;
    public int z;

    protected AlternateDimensionCoordinates(int x, int y, int z) {
        super(x, y, z);
    }

    public static AlternateDimensionCoordinates get() {
        double[] coordinates = fetchRawCoordinates();

        double scaleFactor = 0.125; // Overworld -> Nether
        assert client.level != null;
        if (client.level.dimensionTypeRegistration().getRegisteredName().equals("minecraft:the_nether")) {
            scaleFactor = 8; // Nether -> Overworld
        }
        return new AlternateDimensionCoordinates(
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        );
    }

}
