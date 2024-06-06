package net.hawkelele.quickinfopanel.providers;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.hawkelele.quickinfopanel.config.Config;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PanelHidingOnGameMessageProvider implements ProviderInterface {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Future<?> timeDelay;

    public void register() {
        /*
         * Every time a "Game message" (i.e.: a message that appears in the ActionBar) is received by the client
         * the panel should be hidden.
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
                QuickInfoPanel.PANEL.hide();
            }

            // Apply a time delay before showing the panel again
            if (Config.get().displayPanel) {
                timeDelay = scheduler.schedule(QuickInfoPanel.PANEL::show, 3, TimeUnit.SECONDS);
            }
            return true;
        });

    }
}
