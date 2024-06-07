package net.hawkelele.quickinfopanel.gui;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.ConfigData;
import net.minecraft.client.MinecraftClient;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class Panel {
    private static boolean displayed = true;
    public static final MinecraftClient client = MinecraftClient.getInstance();


    /**
     * Translates in-game ticks to a human-readable 24h clock format
     *
     * @return The current time of the day in a 24h hh:mm format
     */
    private static String getCurrentClock() {
        assert client.world != null;
        long ticks = client.world.getTimeOfDay();
        float hours = (((float) ticks / 1000) + 6) % 24;
        float minutes = (hours * 60) % 60;

        return String.format("%s:%s",
                StringUtils.leftPad(String.valueOf((int) Math.floor(hours)), 2, "0"),
                StringUtils.leftPad(String.valueOf((int) Math.floor(minutes)), 2, "0")
        );
    }

    /**
     * Shows the current facing direction as a single uppercase letter
     *
     * @return N, S, E, W according to the current player's facing cardinal direction
     */
    private static String getCurrentFacingCardinalDirection() {
        assert client.player != null;
        return StringUtils
                .capitalize(client.player.getHorizontalFacing().getName())
                .substring(0, 1);
    }

    public static int[] getTextPosition(String text) {
        assert client.player != null;

        // Overlay placement
        int textPosX = Config.get().position.x;
        int textPosY = Config.get().position.y;

        // Default position: above inventory, with special behaviour
        if (Config.get().position.useDefault) {
            // Number of HUD rows the text should be pushed up based on what elements are displayed
            int modifier = client.player.isInCreativeMode() ? 0 :
                    (int) Math.floor(client.player.getHealth() / 10.0) - 1
                            + (int) Math.floor(client.player.getAbsorptionAmount() / 10.0);
            textPosY = client.getWindow().getScaledHeight() - (70 + (modifier * 10)) + (client.textRenderer.fontHeight);
            textPosX = Math.round(((float) client.getWindow().getScaledWidth() / 2) - ((float) client.textRenderer.getWidth(text) / 2));
        }

        return new int[]{textPosX, textPosY};
    }

    public static int[] getAltTextPosition(String text) {
        int[] position = getTextPosition(text);
        int lineHeight = 10;

        if (!Config.get().position.invertLines) {
            lineHeight = -lineHeight;
        }

        if (Config.get().position.useDefault) {
            position[0] -= 10;
        }

        position[1] += lineHeight;

        return position;
    }

    public static @NotNull String getAltPanelText() {
        assert client.player != null;
        assert client.world != null;
        return String.format("        (%s %s %s)        ",
                Math.round(client.player.getX() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0)),
                Math.round(client.player.getY() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0)),
                Math.round(client.player.getZ() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0))
        );
    }

    public static @NotNull String getPanelText() {
        assert client.player != null;
        return String.format("%s     %s %s %s     %s",
                // Cardinal/Ordinal facing direction
                getCurrentFacingCardinalDirection(),
                // Coordinates
                Math.round(client.player.getX()),
                Math.round(client.player.getY()),
                Math.round(client.player.getZ()),
                // Clock
                getCurrentClock()
        );
    }

    public static boolean shouldBeDisplayed() {
        return !client.options.hudHidden
                && client.player != null
                && client.world != null
                && !client.getDebugHud().shouldShowDebugHud()
                && Config.get().displayPanel
                && displayed;
    }

    /**
     * Hide the overlay
     */
    public static void hide() {
        displayed = false;
    }

    /**
     * Show the overlay
     */
    public static void show() {
        displayed = true;
    }
}
