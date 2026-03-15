package net.hawkelele.quickinfopanel.services;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.StringUtils;

public class CompassService {
    private static final Minecraft client = Minecraft.getInstance();

    /**
     * Shows the current facing direction as a single uppercase letter
     *
     * @return N, S, E, W according to the current player's facing cardinal direction
     */
    private static String getCurrentFacingCardinalDirection() {
        assert client.player != null;

        return String.valueOf(StringUtils
                .capitalize(client.player.getDirection().toString())
                .charAt(0));
    }

    public static String string() {
        return getCurrentFacingCardinalDirection();
    }

    public static String icon() {
        return "texture/gui/compass/compass_" + getCurrentFacingCardinalDirection().toLowerCase() + ".png";
    }
}
