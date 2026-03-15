package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.hawkelele.quickinfopanel.events.command.ExtraCommand;
import net.hawkelele.quickinfopanel.events.command.PositionCommand;
import net.hawkelele.quickinfopanel.events.keybinds.SecondaryToggleKeybindPressed;
import net.hawkelele.quickinfopanel.registry.CommandRegistry;
import net.hawkelele.quickinfopanel.registry.KeybindRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.hawkelele.quickinfopanel.events.keybinds.MainToggleKeybindPressed;


public class QuickInfoPanel implements ClientModInitializer {
    public static final String MOD_ID = "quick-info-panel";
    public static final KeyMapping.Category KEYBINDCATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    @Override
    public void onInitializeClient() {


        KeybindRegistry.register(
                new MainToggleKeybindPressed(),
                new SecondaryToggleKeybindPressed()
        );

        CommandRegistry.register(
                new ExtraCommand(),
                new PositionCommand()
        );


    }
}
