package net.hawkelele.quickinfopanel.commands;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.text.Text;

import java.io.IOException;

import static net.minecraft.server.command.CommandManager.literal;

public class NetherCommand extends Command {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Text.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip nether [on|off]
     * ---------------------------
     * Enables/disables the display of coordinates from the opposite dimension when the player is in the Overworld/Nether
     */
    public CommandRegistrationCallback handle() {
        return (dispatcher, registryAccess, environment) -> dispatcher.register(
                literal("qip").then(literal("nether")
                        .executes(context -> {
                            try {
                                Config.update((config) -> config.showNetherCoordinates = !config.showNetherCoordinates);
                            } catch (IOException e) {
                                throw INVALID_VALUE.create();
                            }

                            context.getSource().sendFeedback(() -> Text.literal("[QIP] Toggled Nether Coordinates"), false);
                            return 1;
                        })
                )
        );
    }
}
