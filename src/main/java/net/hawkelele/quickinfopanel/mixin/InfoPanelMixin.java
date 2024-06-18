package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.gui.panel.AlternateDimensionPanel;
import net.hawkelele.quickinfopanel.gui.panel.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InfoPanelMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"))
    public void render(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Panel panel = Panel.getInstance();
        if (panel.shouldBeHidden()) {
            return;
        }

        panel.draw(context);

        AlternateDimensionPanel alternateDimensionPanel = AlternateDimensionPanel.getInstance();
        if (alternateDimensionPanel.shouldBeHidden()) {
            return;
        }

        alternateDimensionPanel.draw(context);
    }

}
