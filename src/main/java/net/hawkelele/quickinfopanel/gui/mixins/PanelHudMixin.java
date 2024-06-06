package net.hawkelele.quickinfopanel.gui.mixins;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.apache.commons.lang3.StringUtils;

import java.awt.Color;


public class PanelHudMixin implements HudRenderCallback {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private boolean displayed = true;

    /**
     * Translates in-game ticks to a human-readable 24h clock format
     *
     * @return The current time of the day in a 24h hh:mm format
     */
    private String getCurrentClock() {
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
    private String getCurrentFacingOrdinal() {
        assert client.player != null;
        return StringUtils
                .capitalize(client.player.getHorizontalFacing().getName())
                .substring(0, 1);
    }

    /**
     * The actual overlay render implementation. This will get executed once per HUD render
     *
     * @param drawContext the {@link DrawContext} instance
     * @param tickDelta   Progress for linearly interpolating between the previous and current game state
     */
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (client.options.hudHidden || client.player == null || client.world == null || !Config.get().displayPanel || !displayed) {
            return;
        }

        String content = String.format("%s     %s %s %s     %s",
                // Cardinal/Ordinal facing direction
                getCurrentFacingOrdinal(),
                // Coordinates
                Math.round(client.player.getX()),
                Math.round(client.player.getY()),
                Math.round(client.player.getZ()),
                // Clock
                getCurrentClock()
        );

        String otherDimensionContent = String.format("        (%s %s %s)        ",
                Math.round(client.player.getX() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0)),
                Math.round(client.player.getY() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0)),
                Math.round(client.player.getZ() * (client.world.getDimension().coordinateScale() == 1.0 ? 0.125 : 8.0))
        );

        // Initial vertical position for the overlay
        int startingPosY = 65;

        // Number of HUD rows the text should be pushed up based on what elements are displayed
        int modifier = client.player.isInCreativeMode() ? 0 :
                (int) Math.floor(client.player.getHealth() / 10.0)
                        + (int) Math.floor(client.player.getAbsorptionAmount() / 10.0);


        // Overlay placement
        int textPosX = Math.round(((float) client.getWindow().getScaledWidth() / 2) - ((float) client.textRenderer.getWidth(content) / 2));
        int altTextPosX = Math.round(((float) client.getWindow().getScaledWidth() / 2) - ((float) client.textRenderer.getWidth("N   " + otherDimensionContent) / 2));
        int textPosY = client.getWindow().getScaledHeight() - (startingPosY + (modifier * 10));
        int linePosY = textPosY + (client.textRenderer.fontHeight);

        // Render the overlay
        drawContext.drawText(
                client.textRenderer,
                content,
                textPosX,
                linePosY,
                Color.decode("#EEEEEE").hashCode(),
                true
        );

        if (Config.get().showNetherCoordinates) {
            drawContext.drawText(
                    client.textRenderer,
                    otherDimensionContent,
                    altTextPosX,
                    linePosY - 10,
                    Color.decode("#AAAAAA").hashCode(),
                    true
            );
        }

    }

    /**
     * Hide the overlay
     */
    public void hide() {
        displayed = false;
    }

    /**
     * Show the overlay
     */
    public void show() {
        displayed = true;
    }

}
