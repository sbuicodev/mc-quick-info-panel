package net.hawkelele.quickinfopanel.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import net.hawkelele.quickinfopanel.client.gui.hud.PanelHud;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class QuickInfoPanel implements ClientModInitializer {
    public static PanelHud panel = new PanelHud();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Future<?> timeDelay;

    @Override
    public void onInitializeClient() {
        // On each in-game HUD render, the coordinates overlay gets rendered with it
        HudRenderCallback.EVENT.register(panel);

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
                panel.hide();
            }

            // Apply a time delay before showing the coordinates overlay again
            timeDelay = scheduler.schedule(() -> panel.show(), 3, TimeUnit.SECONDS);
            return true;
        });
    }

}
