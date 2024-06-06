package net.hawkelele.quickinfopanel.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.text.Text;

import java.io.IOException;
import java.util.Arrays;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class NetherCommand implements CommandInterface {
    public static final SimpleCommandExceptionType INVALID_VALUE = new SimpleCommandExceptionType(Text.literal("Invalid value"));

    /*
     * ---------------------------
     *   /qip nether [on|off]
     * ---------------------------
     * Enables/disables the display of coordinates from the opposite dimension when the player is in the Overworld/Nether
     */
    public void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                literal("qip").then(literal("nether").then(argument("value", StringArgumentType.greedyString())
                        .executes(context -> {
                            String[] allowed = {"on", "off"};
                            String value = StringArgumentType.getString(context, "value");

                            if (!Arrays.asList(allowed).contains(value)) {
                                throw INVALID_VALUE.create();
                            }

                            try {
                                Config.update((config) -> config.showNetherCoordinates = value.equals("on"));
                            } catch (IOException e) {
                                throw INVALID_VALUE.create();
                            }

                            context.getSource().sendFeedback(() -> Text.literal("[QIP] Set show Nether coordinates to '" + value + "'"), false);
                            return 1;
                        })
                ))));
    }
}
