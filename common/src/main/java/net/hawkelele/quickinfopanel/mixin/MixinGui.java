package net.hawkelele.quickinfopanel.mixin;

import net.hawkelele.quickinfopanel.providers.client.OverlayMessageStatusProvider;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class MixinGui {
    @Shadow
    private Component overlayMessageString;

    @Shadow
    private int overlayMessageTime;

    @Inject(method = "setOverlayMessage", at = @At("TAIL"))
    private void quickInfoPanel$trackOverlayMessage(Component component, boolean animateColor, CallbackInfo ci) {
        syncOverlayMessageState();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void quickInfoPanel$syncOverlayMessageState(boolean pauseAwareTick, CallbackInfo ci) {
        syncOverlayMessageState();
    }

    private void syncOverlayMessageState() {
        OverlayMessageStatusProvider.setDisplaying(overlayMessageString != null && overlayMessageTime > 0);
    }
}
