package net.hawkelele.quickinfopanel.commands;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

import org.apache.commons.lang3.ArrayUtils;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.network.chat.Component;

public class PositionCommand extends Command {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Component.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip position [default|top-left]
     * ---------------------------
     */
    public ClientCommandRegistrationCallback handle() {
        return (dispatcher, registryAccess) -> dispatcher.register(
                literal("qip").then(literal("position")
                        .then(argument("value", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String value = StringArgumentType.getString(context, "value");
                                    if (!ArrayUtils.contains(LayoutRegistry.list(), value)) {
                                        throw INVALID_VALUE.create();
                                    }

                                    config.update((config) -> config.layout = (value));
                                    return 1;
                                })
                        )
                )

        );
    }
}
