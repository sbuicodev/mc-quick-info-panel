package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.gui.panel.AlternateDimensionPanel;
import net.hawkelele.quickinfopanel.gui.panel.Panel;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HideInfoOnOverlayMessageMixin {
    @Inject(method = "renderOverlayMessage", at = @At("TAIL"))
    public void hide(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Panel.getInstance().hide();
        AlternateDimensionPanel.getInstance().hide();
    }

    @Inject(method = "renderOverlayMessage", at = @At("HEAD"))
    public void show(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Panel.getInstance().show();
        AlternateDimensionPanel.getInstance().show();
    }
}
