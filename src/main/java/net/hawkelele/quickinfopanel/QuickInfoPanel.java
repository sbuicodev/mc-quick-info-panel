package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.command.ExtraCommand;
import net.hawkelele.quickinfopanel.command.PositionCommand;
import net.hawkelele.quickinfopanel.event.ToggleAlternateDimensionInfoOnKeypress;
import net.hawkelele.quickinfopanel.registry.Commands;
import net.hawkelele.quickinfopanel.registry.Services;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.hawkelele.quickinfopanel.event.ToggleInfoOnKeypress;


public class QuickInfoPanel implements ClientModInitializer {
    public static final String MOD_ID = "quick-info-panel";
    public static final KeyMapping.Category KEYBINDCATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    @Override
    public void onInitializeClient() {


        Services.register(
                new ToggleInfoOnKeypress(),
                new ToggleAlternateDimensionInfoOnKeypress()
        );

        Commands.register(
                new ExtraCommand(),
                new PositionCommand()
        );


    }
}
