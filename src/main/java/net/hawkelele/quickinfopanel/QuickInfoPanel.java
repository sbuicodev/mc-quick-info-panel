package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.command.NetherCommand;
import net.hawkelele.quickinfopanel.command.PositionCommand;
import net.hawkelele.quickinfopanel.event.ToggleAlternateDimensionInfoOnKeypress;
import net.hawkelele.quickinfopanel.registry.Commands;
import net.hawkelele.quickinfopanel.registry.Services;
import net.hawkelele.quickinfopanel.event.ToggleInfoOnKeypress;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class QuickInfoPanel implements ClientModInitializer {
    public static final String MOD_ID = "quickinfopanel";
    public static final KeyBinding.Category KEYBINDCATEGORY = KeyBinding.Category.create(Identifier.of(MOD_ID, "main"));

    @Override
    public void onInitializeClient() {


        Services.register(
                new ToggleInfoOnKeypress(),
                new ToggleAlternateDimensionInfoOnKeypress()
        );

        Commands.register(
                new NetherCommand(),
                new PositionCommand()
        );


    }
}
