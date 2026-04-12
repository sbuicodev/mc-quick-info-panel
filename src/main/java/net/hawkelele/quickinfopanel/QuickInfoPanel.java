package net.hawkelele.quickinfopanel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.hawkelele.quickinfopanel.commands.ExtraCommand;
import net.hawkelele.quickinfopanel.commands.PositionCommand;
import net.hawkelele.quickinfopanel.events.ActionBarCallback;
import net.hawkelele.quickinfopanel.input.SecondaryPanelToggleKeyPress;
import net.hawkelele.quickinfopanel.gui.Panel;
import net.hawkelele.quickinfopanel.providers.client.OverlayMessageStatusProvider;
import net.hawkelele.quickinfopanel.registry.CommandRegistry;
import net.hawkelele.quickinfopanel.registry.KeybindRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.resources.Identifier;
import net.hawkelele.quickinfopanel.input.PanelToggleKeyPress;


public class QuickInfoPanel implements ClientModInitializer {
    public static final String MOD_ID = "quick-info-panel";
    public static final KeyMapping.Category KEYBINDCATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "main"));

    @Override
    public void onInitializeClient() {
        Panel panel = new Panel();

        // Attach our rendering code to before the chat hud layer. Our layer will render right before the chat. The API will take care of z spacing.
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(MOD_ID, "qip"), panel);


        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
                panel.hideFor(60);

        });

        ClientTickEvents.END_CLIENT_TICK.register((minecraft) -> panel.tick());


        KeybindRegistry.register(
                new PanelToggleKeyPress(),
                new SecondaryPanelToggleKeyPress()
        );

        CommandRegistry.register(
                new ExtraCommand(),
                new PositionCommand()
        );


    }
}
