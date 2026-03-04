package net.hawkelele.quickinfopanel.services;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;

public class Coordinates {
    protected static final Minecraft client = Minecraft.getInstance();

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

    public static String string() {
        return get().toString();
    }

    public static Coordinates getOpposite() {
        double[] coordinates = fetchRawCoordinates();

        double scaleFactor = 0.125; // Overworld -> Nether
        assert client.level != null;
        if (client.level.dimensionTypeRegistration().getRegisteredName().equals("minecraft:the_nether")) {
            scaleFactor = 8; // Nether -> Overworld
        }

        return new Coordinates(
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        );
    }

    public int[] toArray() {
        return new int[]{x, y, z};
    }

    public String[] toArrayOfStrings() {
        int[] numbers = this.toArray();
        return new String[]{
                String.valueOf(numbers[0]),
                String.valueOf(numbers[1]),
                String.valueOf(numbers[2])
        };
    }

    public String toString() {
        return Strings.join(Coordinates.get().toArrayOfStrings(), " ");
    }
}
