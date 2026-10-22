package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.DirectionProvider;

import java.util.Arrays;

public class Compass {
    private final String[] allowedDirections = {"north", "south", "east", "west"};

    public DirectionProvider directionProvider;

    public Compass(DirectionProvider directionProvider) {
        this.directionProvider = directionProvider;
    }

    /**
     * Shows the current facing direction as a single uppercase letter
     *
     * @return N, S, E, W according to the current player's facing cardinal direction
     */
    public String getCurrentFacingCardinalDirection() {
        String direction = directionProvider.getDirection();

        if (!Arrays.asList(allowedDirections).contains(direction)) {
            // Fallback for invalid directions
            return "?";
        }

        return String.valueOf(Character.toUpperCase(direction.charAt(0)));
    }

    /**
     * Shows the axis and sign the player is facing
     *
     * @return Z-, Z+, X-, X+ for N, S, W, E, or an empty string for invalid directions
     */
    public String getFacingAxis() {
        return switch (getCurrentFacingCardinalDirection()) {
            case "N" -> "Z-";
            case "S" -> "Z+";
            case "W" -> "X-";
            case "E" -> "X+";
            default -> "";
        };
    }

    public String getIconPath() {
        return "texture/gui/compass/compass_" + getCurrentFacingCardinalDirection().toLowerCase() + ".png";
    }
}
