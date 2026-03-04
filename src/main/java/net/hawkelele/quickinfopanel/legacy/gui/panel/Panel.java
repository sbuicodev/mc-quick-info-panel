package net.hawkelele.quickinfopanel.legacy.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.legacy.gui.Icon;
import net.hawkelele.quickinfopanel.legacy.gui.coordinates.Coordinates;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

public class Panel {
    private static Panel instance;

    protected boolean hidden = false;

    public final Minecraft client = Minecraft.getInstance();

    private final Component SEPARATOR = Component.literal("   ");


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
        assert client.level != null;
        long ticks = client.level.getDayTime();
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
                .capitalize(client.player.getDirection().toString())
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
            textPosX = client.getWindow().getGuiScaledWidth() - textPosX;
        }
        if (Config.getInstance().settings().position.invertedY) {
            textPosY = client.getWindow().getGuiScaledHeight() - textPosY;
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
        return Math.round(((float) client.getWindow().getGuiScaledWidth() / 2) - ((float) width / 2));
    }

    public @NotNull void draw(GuiGraphics context) {
        assert client.player != null;
        assert client.level != null;

        Component text = Component.empty();

        int[] position = getTextPosition();
        int x = position[0];
        int y = position[1];

        /* ---------------------------------------------
         *  Compass and cardinal directions
         * --------------------------------------------
         */
        String direction = getCurrentFacingCardinalDirection();

        Icon compassIcon = Icon.of("texture/gui/compass/compass_" + StringUtils.lowerCase(direction) + ".png", x, y);

        text = Component.empty().append(text)
                   .append("    ")
                   .append(Component.literal(direction))
                   .append(SEPARATOR);



        /* ---------------------------------------------
         *  Coordinates
         * --------------------------------------------
         */
        Coordinates coordinates = Coordinates.get();

        text = Component.empty().append(text)
                   .append(Component.literal("XYZ: ").withStyle(ChatFormatting.YELLOW))
                   .append(Component.literal(String.format("%s %s %s", coordinates.x, coordinates.y, coordinates.z)))
                   .append(SEPARATOR);

        /* ---------------------------------------------
         *  Clock
         * --------------------------------------------
         */
        String clock = getCurrentClock();
        Icon clockIcon = Icon.of("texture/gui/clock/clock_" + (getCurrentTime()[0] >= 18 ? "night" : "day") + ".png", x + client.font.width(text), y);

        text = Component.empty().append(text)
                   .append("  ")
                   .append(Component.literal(" " + clock));

        if (Config.getInstance().settings().position.centered) {
            x = getCenteredTextHorizontalPosition(client.font.width(text));
            compassIcon.offsetX(x);
            clockIcon.offsetX(x);
        }

        compassIcon.draw(context);
        clockIcon.draw(context);

        context.drawString(client.font,
                text,
                x, y, CommonColors.WHITE, true
        );

    }

    public boolean shouldBeHidden() {
        return client.options.hideGui
                || client.player == null
                || client.level == null
                || client.debugEntries.isOverlayVisible()
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
