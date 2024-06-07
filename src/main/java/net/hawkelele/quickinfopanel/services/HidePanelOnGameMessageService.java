package net.hawkelele.quickinfopanel.services;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.Panel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HidePanelOnGameMessageService extends Service<ClientReceiveMessageEvents.AllowGame> {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Future<?> timeDelay;

    @Override
    public Event<ClientReceiveMessageEvents.AllowGame> event() {
        return ClientReceiveMessageEvents.ALLOW_GAME;
    }

    @Override
    public ClientReceiveMessageEvents.AllowGame handle() {
        /*
         * Every time a "Game message" (i.e.: a message that appears in the ActionBar) is received by the client
         * the panel should be hidden.
         *
         * Since there's no event handlers for the moment the message disappears, a time delay is applied instead.
         */
        return ((message, overlay) -> {
            // Reset any running visibility timer
            if (timeDelay != null && !timeDelay.isCancelled()) {
                timeDelay.cancel(true);
            }

            // Only hide the overlay if the incoming game message is to be displayed to the user
            if (overlay) {
                Panel.hide();
            }

            // Apply a time delay before showing the panel again
            if (Config.get().displayPanel) {
                timeDelay = scheduler.schedule(Panel::show, 3, TimeUnit.SECONDS);
            }
            return true;

        });
    }
}
