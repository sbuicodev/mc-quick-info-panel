package net.hawkelele.quickinfopanel.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;

public abstract class Command {
    private final Event<ClientCommandRegistrationCallback> event = ClientCommandRegistrationCallback.EVENT;

    public void register() {
        event.register(handle());
    }

    public abstract ClientCommandRegistrationCallback handle();
}
