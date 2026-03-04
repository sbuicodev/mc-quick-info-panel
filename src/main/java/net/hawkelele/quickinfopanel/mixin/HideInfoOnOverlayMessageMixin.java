package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.legacy.gui.panel.SecondaryPanel;
import net.hawkelele.quickinfopanel.legacy.gui.panel.Panel;
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
        Panel.getInstance().hide();
        SecondaryPanel.getInstance().hide();
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"))
    public void show(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Panel.getInstance().show();
        SecondaryPanel.getInstance().show();
    }
}
