package net.hawkelele.quickinfopanel.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.event.Event;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.provider.ConfigProvider;

public abstract class Command {
    protected final Event<ClientCommandRegistrationCallback> event = ClientCommandRegistrationCallback.EVENT;
    protected final Config config = ConfigProvider.getConfig();


    public void register() {
        event.register(handle());
    }

    public abstract ClientCommandRegistrationCallback handle();
}
