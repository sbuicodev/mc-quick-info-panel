package net.hawkelele.quickinfopanel.commands;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.text.Text;

import java.io.IOException;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NetherCommand extends Command {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Text.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip nether [on|off]
     * ---------------------------
     * Enables/disables the display of coordinates from the opposite dimension when the player is in the Overworld/Nether
     */
    public ClientCommandRegistrationCallback handle() {
        return (dispatcher, registryAccess) -> dispatcher.register(
                literal("qip").then(literal("nether")
                        .executes(context -> {
                            try {
                                Config.update((config) -> config.showNetherCoordinates = !config.showNetherCoordinates);
                            } catch (IOException e) {
                                throw INVALID_VALUE.create();
                            }

                            context.getSource().sendFeedback(Text.literal("[QIP] Toggled Nether Coordinates"));
                            return 1;
                        })
                )
        );
    }
}
