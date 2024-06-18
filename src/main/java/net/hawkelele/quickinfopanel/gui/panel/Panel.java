package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.Icon;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class Panel {
    private static Panel instance;

    protected boolean hidden = false;

    public final MinecraftClient client = MinecraftClient.getInstance();

    private final Text SEPARATOR = Text.literal("   ");


    /**
     * Translates in-game ticks to a human-readable 24h clock format
     *
     * @return The current time of the day in a 24h hh:mm format
     */
    private String getCurrentClock() {
        int[] time = getCurrentTime();

        return String.format(" %s:%s",
                StringUtils.leftPad(String.valueOf(time[0]), 2, "0"),
                StringUtils.leftPad(String.valueOf(time[1]), 2, "0")
        );
    }

    private int[] getCurrentTime() {
        assert client.world != null;
        long ticks = client.world.getTimeOfDay();
        float hours = (((float) ticks / 1000) + 6) % 24;
        float minutes = (hours * 60) % 60;

        return new int[]{(int) Math.floor(hours), (int) Math.floor(minutes)};
    }

    /**
     * Shows the current facing direction as a single uppercase letter
     *
     * @return N, S, E, W according to the current player's facing cardinal direction
     */
    private String getCurrentFacingCardinalDirection() {
        assert client.player != null;

        return String.valueOf(StringUtils
                .capitalize(client.player.getHorizontalFacing().getName())
                .charAt(0));
    }

    public static Panel getInstance() {
        return instance == null ? instance = new Panel() : instance;
    }

    public int[] getTextPosition() {
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

        return new int[]{textPosX, textPosY};
    }

    public int[] getTextPosition(int width) {
        assert client.player != null;
        // Overlay placement
        int[] position = getTextPosition();

        int textPosX = Config.getInstance().settings().position.centered ? getCenteredTextHorizontalPosition(width) : position[0];
        int textPosY = position[1];

        return new int[]{textPosX, textPosY};
    }

    private int getCenteredTextHorizontalPosition(int width) {
        return Math.round(((float) client.getWindow().getScaledWidth() / 2) - ((float) width / 2));
    }

    public @NotNull void draw(DrawContext context) {
        assert client.player != null;
        Text text = Text.empty();

        int[] position = getTextPosition();
        int x = position[0];
        int y = position[1];

        /* ---------------------------------------------
         *  Compass and cardinal directions
         * --------------------------------------------
         */
        String direction = getCurrentFacingCardinalDirection();

        Icon compassIcon = Icon.of("texture/gui/compass/compass_" + StringUtils.lowerCase(direction) + ".png", x, y);

        text = Text.empty().append(text)
                   .append("    ")
                   .append(Text.literal(direction))
                   .append(SEPARATOR);



        /* ---------------------------------------------
         *  Coordinates
         * --------------------------------------------
         */
        Coordinates coordinates = Coordinates.get();

        text = Text.empty().append(text)
                   .append(Text.literal("XYZ: ").formatted(Formatting.YELLOW))
                   .append(Text.literal(String.format("%s %s %s", coordinates.x, coordinates.y, coordinates.z)))
                   .append(SEPARATOR);

        /* ---------------------------------------------
         *  Clock
         * --------------------------------------------
         */
        String clock = getCurrentClock();
        Icon clockIcon = Icon.of("texture/gui/clock/clock_" + (getCurrentTime()[0] >= 18 ? "night" : "day") + ".png", x + client.textRenderer.getWidth(text), y);

        text = Text.empty().append(text)
                   .append("  ")
                   .append(Text.literal(" " + clock));

        if (Config.getInstance().settings().position.centered) {
            x = getCenteredTextHorizontalPosition(client.textRenderer.getWidth(text));
            compassIcon.offsetX(x);
            clockIcon.offsetX(x);
        }

        compassIcon.draw(context);
        clockIcon.draw(context);

        context.drawText(client.textRenderer,
                text,
                x, y, Colors.WHITE, true
        );

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
