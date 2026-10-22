package net.hawkelele.quickinfopanel.registry;

import net.hawkelele.quickinfopanel.commands.QipCommands;
import net.hawkelele.quickinfopanel.platform.Services;
import net.hawkelele.quickinfopanel.platform.services.ICommandsHelper.CommandRegistration;

import java.util.Set;

public class CommandsRegistry {
    public static final Set<CommandRegistration> COMMANDS = Set.of(
            QipCommands::register
    );

    public static void registerCommands() {
        for (CommandRegistration command : COMMANDS) {
            Services.COMMANDS.registerCommand(command);
        }
    }
}
