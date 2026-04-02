package net.hawkelele.quickinfopanel.services;

import joptsimple.internal.Strings;
import net.hawkelele.quickinfopanel.providers.DimensionProvider;
import net.hawkelele.quickinfopanel.providers.PositionProvider;

import java.util.Map;

import static java.util.Map.entry;

public class Coordinates {
    private final PositionProvider positionProvider;
    private final DimensionProvider dimensionProvider;

    public Coordinates(PositionProvider positionProvider, DimensionProvider dimensionProvider) {
        this.positionProvider = positionProvider;
        this.dimensionProvider = dimensionProvider;
    }

    private static final Map<String, String> oppositeDimensions = Map.ofEntries(
            entry("minecraft:overworld", "minecraft:the_nether"),
            entry("minecraft:the_nether", "minecraft:overworld")
    );

    private static final Map<String, String> icons = Map.ofEntries(
            entry("minecraft:overworld", "\uD83C\uDF33"),
            entry("minecraft:the_nether", "\uD83D\uDD25")
    );


    public String getCurrentDimension() {
        return dimensionProvider.getDimension();
    }


    public boolean hasOppositeDimension() {
        return oppositeDimensions.containsKey(getCurrentDimension());
    }


    public String getOppositeDimension() {
        return oppositeDimensions.get(getCurrentDimension());
    }

    public int[] getCurrentPosition() {
        double[] coordinates = positionProvider.getPosition();
        return new int[]{
                (int) Math.floor(coordinates[0]),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2])
        };
    }

    public int[] getOppositeDimensionPosition() {
        assert hasOppositeDimension();

        double[] coordinates = positionProvider.getPosition();
        String oppositeDimension = getOppositeDimension();

        double scaleFactor = 0.125; // Overworld -> Nether
        if (oppositeDimension.equals("minecraft:overworld")) {
            scaleFactor = 8; // Nether -> Overworld
        }
        return new int[]{
                (int) Math.floor(coordinates[0] * scaleFactor),
                (int) Math.floor(coordinates[1]),
                (int) Math.floor(coordinates[2] * scaleFactor)
        };
    }

    public String getDimensionIcon(String dimension) {
        return icons.getOrDefault(dimension, "");
    }

    public static String toString(int[] coordinates) {
        return Strings.join(new String[]{
                String.valueOf(coordinates[0]),
                String.valueOf(coordinates[1]),
                String.valueOf(coordinates[2])
        }, " ");
    }

    public static String toShortString(int[] coordinates) {
        return Strings.join(new String[]{
                String.valueOf(coordinates[0]),
                String.valueOf(coordinates[2])
        }, " ");
    }
}
