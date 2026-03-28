package net.hawkelele.quickinfopanel.mixin;


import net.hawkelele.quickinfopanel.registry.PanelRegistry;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class OverlayRenderMixin {

    @Inject(method = "extractOverlayMessage", at = @At("TAIL"))
    public void hide(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        Layout main = PanelRegistry.get("main");

        if (main != null) {
            main.hide();
        }
    }

    @Inject(method = "extractOverlayMessage", at = @At("HEAD"))
    public void show(GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        Layout main = PanelRegistry.get("main");

        if (main != null) {
            main.show();
        }
    }
}
