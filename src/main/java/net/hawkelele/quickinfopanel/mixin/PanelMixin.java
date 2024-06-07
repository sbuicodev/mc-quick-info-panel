package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public class PanelMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (!Panel.shouldBeDisplayed()) {
            return;
        }

        String panelText = Panel.getPanelText();
        int[] textPosition = Panel.getTextPosition(panelText);
        context.drawText(
                client.textRenderer,
                panelText,
                textPosition[0],
                textPosition[1],
                Color.decode("#EEEEEE").hashCode(),
                true
        );

        if (Config.get().showNetherCoordinates) {
            String altPanelText = Panel.getAltPanelText();
            int[] altTextPosition = Panel.getAltTextPosition(altPanelText);
            context.drawText(
                    client.textRenderer,
                    altPanelText,
                    altTextPosition[0],
                    altTextPosition[1],
                    Color.decode("#AAAAAA").hashCode(),
                    true
            );
        }

    }


}
