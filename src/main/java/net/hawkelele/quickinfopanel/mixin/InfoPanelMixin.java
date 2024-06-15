package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.gui.panel.AlternateDimensionPanel;
import net.hawkelele.quickinfopanel.gui.panel.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public class InfoPanelMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        Panel panel = Panel.getInstance();
        if (panel.shouldBeHidden()) {
            return;
        }

        Text panelText = panel.getPanelText();
        int[] textPosition = panel.getTextPosition(panelText);
        context.drawText(
                client.textRenderer,
                panelText,
                textPosition[0], textPosition[1],
                Color.decode("#EEEEEE").hashCode(),
                true
        );

        AlternateDimensionPanel alternateDimensionPanel = AlternateDimensionPanel.getInstance();
        if (alternateDimensionPanel.shouldBeHidden()) {
            return;
        }

        Text alternateDimensionText = alternateDimensionPanel.getPanelText();
        int[] altTextPosition = alternateDimensionPanel.getTextPosition(alternateDimensionText);
        context.drawText(
                client.textRenderer,
                alternateDimensionText,
                altTextPosition[0], altTextPosition[1],
                Color.decode("#AAAAAA").hashCode(),
                true
        );
    }

}
