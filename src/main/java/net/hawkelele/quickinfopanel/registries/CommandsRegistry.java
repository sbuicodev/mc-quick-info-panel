package net.hawkelele.quickinfopanel.registries;


import net.hawkelele.quickinfopanel.commands.Command;

public class CommandsRegistry {

    /**
     * Registers the provided list of commands
     *
     * @param handlers The list of handlers for which the "register" method will be executed
     */
    public static void register(Command... handlers) {
        for (Command handler : handlers) {
            handler.register();
        }
    }
}
