package net.hawkelele.quickinfopanel.registries;


import net.hawkelele.quickinfopanel.commands.CommandInterface;

public class CommandsRegistry {

    /**
     * Registers the provided list of commands
     *
     * @param handlers The list of handlers for which the "register" method will be executed
     */
    public static void register(CommandInterface... handlers) {
        for (CommandInterface handler : handlers) {
            handler.register();
        }
    }
}
