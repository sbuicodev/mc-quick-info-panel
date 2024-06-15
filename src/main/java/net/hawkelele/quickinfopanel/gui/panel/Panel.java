package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Panel {
    private static Panel instance;

    protected boolean hidden = false;

    public final MinecraftClient client = MinecraftClient.getInstance();

    /**
     * Translates in-game ticks to a human-readable 24h clock format
     *
     * @return The current time of the day in a 24h hh:mm format
     */
    private Text getCurrentClock() {
        assert client.world != null;
        long ticks = client.world.getTimeOfDay();
        float hours = (((float) ticks / 1000) + 6) % 24;
        float minutes = (hours * 60) % 60;

        return Text.empty().append(Text.literal("\uD83D\uDD51").formatted(Formatting.YELLOW))
                   .append(String.format(" %s:%s",
                           StringUtils.leftPad(String.valueOf((int) Math.floor(hours)), 2, "0"),
                           StringUtils.leftPad(String.valueOf((int) Math.floor(minutes)), 2, "0")
                   ));
    }

    /**
     * Shows the current facing direction as a single uppercase letter
     *
     * @return N, S, E, W according to the current player's facing cardinal direction
     */
    private Text getCurrentFacingCardinalDirection() {
        assert client.player != null;
        HashMap<String, String> arrows = new HashMap<>();
        arrows.put("N", "∧");
        arrows.put("E", "<");
        arrows.put("W", ">");
        arrows.put("S", "∨");

        String direction = String.valueOf(StringUtils
                .capitalize(client.player.getHorizontalFacing().getName())
                .charAt(0));

        return Text.empty()
                   .append(Text.literal(arrows.get(direction)).formatted(Formatting.YELLOW))
                   .append(Text.literal(" "))
                   .append(Text.literal(direction));
    }

    public static Panel getInstance() {
        return instance == null ? instance = new Panel() : instance;
    }

    public int[] getTextPosition(Text text) {
        assert client.player != null;
        // Overlay placement
        int textPosX = Config.getInstance().settings().position.x;
        int textPosY = Config.getInstance().settings().position.y;

        if (Config.getInstance().settings().position.invertedX) {
            textPosX = client.getWindow().getScaledWidth() - textPosX;
        }
        if (Config.getInstance().settings().position.invertedY) {
            textPosY = client.getWindow().getScaledHeight() - textPosY;
        }

        if (Config.getInstance().settings().position.centered) {
            textPosX = Math.round(((float) client.getWindow().getScaledWidth() / 2) - ((float) client.textRenderer.getWidth(text) / 2));
        }

        return new int[]{textPosX, textPosY};
    }

    public @NotNull Text getPanelText() {
        assert client.player != null;
        Coordinates coordinates = Coordinates.get();

        return Text.empty()
                   // Cardinal/Ordinal facing direction
                   .append(getCurrentFacingCardinalDirection())
                   .append("   ")
                   // Coordinates
                   .append(Text.literal("XYZ: ").formatted(Formatting.YELLOW))
                   .append(String.format("%s %s %s", coordinates.x, coordinates.y, coordinates.z))

                   .append("   ")
                   // Clock
                   .append(getCurrentClock());
    }

    public boolean shouldBeHidden() {
        return client.options.hudHidden
                || client.player == null
                || client.world == null
                || client.getDebugHud().shouldShowDebugHud()
                || !Config.getInstance().settings().displayPanel
                || (hidden && Config.getInstance().settings().position.hideWithActionbar);
    }

    /**
     * Hide the overlay
     */
    public void hide() {
        hidden = true;
    }

    /**
     * Show the overlay
     */
    public void show() {
        hidden = false;
    }
}
