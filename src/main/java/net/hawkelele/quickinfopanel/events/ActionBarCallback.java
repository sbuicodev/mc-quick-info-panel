package net.hawkelele.quickinfopanel.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;

public interface ActionBarCallback {
    void onActionBarMessage(Component message, boolean overlay);

    Event<ActionBarCallback> EVENT = EventFactory.createArrayBacked(ActionBarCallback.class,
            (listeners) -> (message, overlay) -> {
                for (ActionBarCallback listener : listeners) {
                    listener.onActionBarMessage(message, overlay);
                }
            });
}