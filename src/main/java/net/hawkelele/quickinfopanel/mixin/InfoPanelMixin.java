package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.gui.panel.SecondaryPanel;
import net.hawkelele.quickinfopanel.gui.panel.Panel;
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
public class InfoPanelMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"))
    public void render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        // Main panel
        Panel panel = Panel.getInstance();
        if (panel.shouldBeHidden()) {
            return; // If the main panel is hidden, no other panels should be drawn
        }

        panel.draw(context);

        // Other panels
        SecondaryPanel secondaryPanel = SecondaryPanel.getInstance();
        if (!secondaryPanel.shouldBeHidden()) {
            secondaryPanel.draw(context);
        }
    }

}
