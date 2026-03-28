package net.hawkelele.quickinfopanel.events.command;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.network.chat.Component;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

public class ExtraCommand extends Command {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Component.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip nether [on|off]
     * ---------------------------
     * Enables/disables the display of coordinates from the opposite dimension when the player is in the Overworld/Nether
     */
    public ClientCommandRegistrationCallback handle() {

        return (dispatcher, registryAccess) -> dispatcher.register(
                literal("qip").then(literal("extra")
                        .executes(context -> {
                            config.update((config) -> config.displaySecondaryPanel = !config.displaySecondaryPanel);
                            return 1;
                        })
                )
        );
    }
}
