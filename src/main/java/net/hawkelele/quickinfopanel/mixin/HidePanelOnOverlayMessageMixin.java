package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.provider.PanelProvider;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HidePanelOnOverlayMessageMixin {
    @Inject(method = "renderOverlayMessage", at = @At("TAIL"))
    public void hide(DrawContext context, float tickDelta, CallbackInfo ci) {
        PanelProvider.getPanel().hide();
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"))
    public void show(DrawContext context, float tickDelta, CallbackInfo ci) {
        PanelProvider.getPanel().show();
    }
}
