package net.hawkelele.quickinfopanel.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.config.Config;
import org.jetbrains.annotations.NotNull;

public abstract class Command {
    protected final Event<@NotNull ClientCommandRegistrationCallback> event = ClientCommandRegistrationCallback.EVENT;
    protected final Config config = Config.getInstance();


    public void register() {
        event.register(handle());
    }

    public abstract ClientCommandRegistrationCallback handle();
}
