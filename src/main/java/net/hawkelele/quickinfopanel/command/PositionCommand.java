package net.hawkelele.quickinfopanel.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.text.Text;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class PositionCommand extends Command {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Text.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip nether [on|off]
     * ---------------------------
     * Enables/disables the display of coordinates from the opposite dimension when the player is in the Overworld/Nether
     */
    public ClientCommandRegistrationCallback handle() {
        return (dispatcher, registryAccess) -> dispatcher.register(
                literal("qip").then(literal("position")
                        .then(argument("value", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String value = StringArgumentType.getString(context, "value");
                                    if (!ArrayUtils.contains(new String[]{"default", "top-left"}, value)) {
                                        throw INVALID_VALUE.create();
                                    }

                                    try {
                                        Config.update((config) -> config.position = value);
                                    } catch (IOException e) {
                                        throw INVALID_VALUE.create();
                                    }
                                    return 1;
                                })
                        )
                )

        );
    }
}
