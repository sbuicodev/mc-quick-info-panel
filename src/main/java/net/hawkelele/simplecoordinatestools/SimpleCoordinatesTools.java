package net.hawkelele.simplecoordinatestools;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.hawkelele.simplecoordinatestools.ui.CoordinatesOverlay;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class SimpleCoordinatesTools implements ClientModInitializer {
    public static CoordinatesOverlay coordinatesOverlay = new CoordinatesOverlay();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Future<?> timeDelay;

    @Override
    public void onInitializeClient() {
        // On each in-game HUD render, the coordinates overlay gets rendered with it
        HudRenderCallback.EVENT.register(coordinatesOverlay);

        /*
         * Every time a "Game message" (i.e.: a message that appears in the ActionBar) is received by the client
         * the coordinates overlay should be hidden.
         *
         * Since there's no event handlers for the moment the message disappears, a time delay is applied instead.
         */
        ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
            // Reset any running visibility timer
            if (timeDelay != null && !timeDelay.isCancelled()) {
                timeDelay.cancel(true);
            }

            // Only hide the overlay if the incoming game message is to be displayed to the user
            if (overlay) {
                coordinatesOverlay.hide();
            }

            // Apply a time delay before showing the coordinates overlay again
            timeDelay = scheduler.schedule(() -> coordinatesOverlay.show(), 3, TimeUnit.SECONDS);
            return overlay;
        });
    }

}
