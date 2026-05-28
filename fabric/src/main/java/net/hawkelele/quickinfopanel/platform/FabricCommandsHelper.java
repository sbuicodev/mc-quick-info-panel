package net.hawkelele.quickinfopanel.platform;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hawkelele.quickinfopanel.platform.services.ICommandsHelper;

public class FabricCommandsHelper implements ICommandsHelper {
    @Override
    public void registerCommand(CommandRegistration command) {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> command.register(dispatcher, buildContext, selection));
    }
}
