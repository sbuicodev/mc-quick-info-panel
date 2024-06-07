package net.hawkelele.quickinfopanel.commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;

public abstract class Command {
    private final Event<CommandRegistrationCallback> event = CommandRegistrationCallback.EVENT;

    public void register() {
        event.register(handle());
    }

    public abstract CommandRegistrationCallback handle();
}
