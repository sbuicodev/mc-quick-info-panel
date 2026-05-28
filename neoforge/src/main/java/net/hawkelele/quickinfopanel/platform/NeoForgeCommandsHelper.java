package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.platform.services.ICommandsHelper;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.HashSet;
import java.util.Set;

public class NeoForgeCommandsHelper implements ICommandsHelper {
    private static final Set<CommandRegistration> COMMANDS = new HashSet<>();
    private static boolean listenerRegistered = false;

    @Override
    public void registerCommand(CommandRegistration command) {
        COMMANDS.add(command);
        if (!listenerRegistered) {
            NeoForge.EVENT_BUS.addListener(NeoForgeCommandsHelper::onRegisterCommands);
            listenerRegistered = true;
        }
    }

    private static void onRegisterCommands(RegisterCommandsEvent event) {
        COMMANDS.forEach(command -> command.register(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection()));
    }
}
