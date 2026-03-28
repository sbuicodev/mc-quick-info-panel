package net.hawkelele.quickinfopanel.services;

import joptsimple.internal.Strings;
import net.minecraft.client.Minecraft;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import static java.util.Map.entry;

public class CoordinatesService {
    protected static final Minecraft client = Minecraft.getInstance();

    public final int x;
    public final int y;
    public final int z;

    private static final Map<String, String> oppositeDimensions = Map.ofEntries(
            entry("minecraft:overworld", "minecraft:the_nether"),
            entry("minecraft:the_nether", "minecraft:overworld")
    );

    private static final Map<String, String> icons = Map.ofEntries(
            entry("minecraft:overworld", "\uD83C\uDF33"),
            entry("minecraft:the_nether", "\uD83D\uDD25")
    );


    protected CoordinatesService(int x, int y, int z) {
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

    public static CoordinatesService get() {
        double[] coordinates = fetchRawCoordinates();
        return new CoordinatesService(
                (int) Math.floor(coordinates[0]),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2])
        );
    }

    public static String string() {
        return get().toString();
    }

    public static CoordinatesService opposite() {
        double[] coordinates = fetchRawCoordinates();

        double scaleFactor = 0.125; // Overworld -> Nether
        assert client.level != null;
        if (getOppositeDimensionId().equals("minecraft:overworld")) {
            scaleFactor = 8; // Nether -> Overworld
        }

        return new CoordinatesService(
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        );
    }

    public static String getCurrentDimensionId() {
        assert client.level != null;
        return client.level.dimensionTypeRegistration().getRegisteredName();
    }

    @Nullable
    public static String getOppositeDimensionId() {
        return oppositeDimensions.getOrDefault(getCurrentDimensionId(), null);
    }

    public static String getOppositeDimensionIcon() {
        assert client.level != null;
        return icons.getOrDefault(getOppositeDimensionId(), null);
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
        return Strings.join(toArrayOfStrings(), " ");
    }
}
