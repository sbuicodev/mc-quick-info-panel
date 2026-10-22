package net.hawkelele.quickinfopanel.fabric.platform;

//? if fabric {
//? if >=1.21.6 {
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
//?} else if >=1.21.4 {
/*import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.gui.LayeredDraw;
*///?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
*///?}
import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.hawkelele.quickinfopanel.gui.core.elements.Element;
import net.hawkelele.quickinfopanel.gui.layouts.RootLayout;
import net.hawkelele.quickinfopanel.platform.services.IHudHelper;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;

public class FabricHudHelper implements IHudHelper {
    //? if >=1.21.6 {
    private class FabricHudPanel implements HudElement {
    //?} else if >=1.21.4 {
    /*private class FabricHudPanel implements LayeredDraw.Layer {
    *///?} else {
    /*private class FabricHudPanel implements HudRenderCallback {
    *///?}
        private Element rootElement;

        public FabricHudPanel(Element element) {
            this.rootElement = element;
        }

        @Override
        //? if >=26.1 {
        public void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        //?} else if >=1.21.4 {
        /*public void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        *///?} else {
        /*public void onHudRender(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        *///?}
            Graphics.register(graphics);
            rootElement.render(0, 0);
        }
    }

    @Override
    public void registerHudRenderCallback() {
        //? if >=1.21.6 {
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "qip"), new FabricHudPanel(
                new RootLayout()
        ));
        //?} else if >=1.21.4 {
        /*// HudElementRegistry only exists from 1.21.6
        HudLayerRegistrationCallback.EVENT.register(layers -> layers.attachLayerBefore(IdentifiedLayer.CHAT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "qip"), new FabricHudPanel(
                new RootLayout()
        )));
        *///?} else {
        /*// HUD layers can only be ordered from 1.21.4, so the panel draws after the vanilla HUD
        HudRenderCallback.EVENT.register(new FabricHudPanel(
                new RootLayout()
        ));
        *///?}
    }
}
//?}
