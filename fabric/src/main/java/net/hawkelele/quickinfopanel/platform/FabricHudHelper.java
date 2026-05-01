package net.hawkelele.quickinfopanel.platform;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.hawkelele.quickinfopanel.gui.core.elements.Element;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.gui.layouts.RootLayout;
import net.hawkelele.quickinfopanel.platform.services.IHudHelper;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class FabricHudHelper implements IHudHelper {
    private class FabricHudPanel implements HudElement {
        private Element rootElement;

        public FabricHudPanel(Element element) {
            this.rootElement = element;
        }

        @Override
        public void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
            Graphics.register(graphics);
            rootElement.render(0, 0);
        }
    }

    @Override
    public void registerHudRenderCallback() {
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "qip"), new FabricHudPanel(
                new RootLayout()
        ));
    }
}
