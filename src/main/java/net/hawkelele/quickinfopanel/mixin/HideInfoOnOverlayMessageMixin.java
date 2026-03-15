package net.hawkelele.quickinfopanel.mixin;


import net.hawkelele.quickinfopanel.PanelManager;
import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.panels.MainPanel;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class HideInfoOnOverlayMessageMixin {
    @Inject(method = "renderOverlayMessage", at = @At("TAIL"))
    public void hide(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Panel main = PanelManager.get("main");

        if (main != null) {
            main.hide();
        }
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"))
    public void show(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Panel main = PanelManager.get("main");

        if (main != null) {
            main.show();
        }
    }
}
