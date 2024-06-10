package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.hawkelele.quickinfopanel.provider.ConfigProvider;
import net.hawkelele.quickinfopanel.provider.PanelProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Panel {
    protected boolean hidden = false;

    protected final Config config = ConfigProvider.getConfig();
    public final AlternateDimensionPanel alternateDimensionPanel = PanelProvider.getAlternateDimensionPanel();

    public final MinecraftClient client = MinecraftClient.getInstance();

    public String getDimensionName() {
        assert client.world != null;
        String dimension = client.world.getDimensionEntry().getIdAsString();
        return switch (dimension) {
            case "minecraft:the_nether" -> "Nether";
            case "minecraft:the_end" -> "End";
            default -> "Overworld";
        };
    }

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

        float yaw = client.player.getYaw();
        yaw += yaw < 0 ? 360 : 0;
        HashMap<String, String> arrows = new HashMap<>();
        arrows.put("N", "▲");
        arrows.put("E", "▶");
        arrows.put("W", "◀");
        arrows.put("S", "▼");

        int i = Math.round(yaw / 45f) % 4;
        String direction = String.valueOf(StringUtils
                .capitalize(client.player.getHorizontalFacing().getName())
                .charAt(0));
        return Text.empty().append(Text.literal(arrows.get(direction)).formatted(Formatting.YELLOW))
                   .append(" ")
                   .append(Text.literal(direction));
    }

    public int[] getTextPosition(Text text) {
        assert client.player != null;
        // Overlay placement
        int textPosX = config.get().position.x;
        int textPosY = config.get().position.y;

        if (config.get().position.invertedX) {
            textPosX = client.getWindow().getScaledWidth() - textPosX;
        }
        if (config.get().position.invertedY) {
            textPosY = client.getWindow().getScaledHeight() - textPosY;
        }

        if (config.get().position.centered) {
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
                || !config.get().displayPanel
                || (hidden && config.get().position.hideWithActionbar);
    }

    /**
     * Hide the overlay
     */
    public void hide() {
        if (alternateDimensionPanel != null) {
            alternateDimensionPanel.hide();
        }
        hidden = true;
    }

    /**
     * Show the overlay
     */
    public void show() {
        if (alternateDimensionPanel != null) {
            alternateDimensionPanel.show();
        }
        hidden = false;
    }
}
