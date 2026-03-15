package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.gui.RootLayout;
import net.hawkelele.quickinfopanel.registry.PanelRegistry;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.Context;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiRenderMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"))
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        // Main panel

        Context.set(context);
        Layout panel = new RootLayout();
        PanelRegistry.register(panel, "main");
        panel.render();

    }

}
