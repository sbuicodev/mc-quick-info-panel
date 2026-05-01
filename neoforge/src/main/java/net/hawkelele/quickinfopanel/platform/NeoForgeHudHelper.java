package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.gui.Graphics;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.gui.layouts.DefaultLayout;
import net.hawkelele.quickinfopanel.gui.layouts.RootLayout;
import net.hawkelele.quickinfopanel.gui.panels.MainPanel;
import net.hawkelele.quickinfopanel.platform.services.IHudHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class NeoForgeHudHelper implements IHudHelper {
    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    private static class GuiRenderSubscriber {
        public static void register() {
            new GuiRenderSubscriber();
        }

        @SubscribeEvent(priority = EventPriority.LOW)
        public static void onRenderGui(RenderGuiEvent.Post event) {
            GuiGraphicsExtractor graphics = event.getGuiGraphics();
            Graphics.register(graphics);
            new RootLayout().render();
        }
    }

    @Override
    public void registerHudRenderCallback() {
        GuiRenderSubscriber.register();
    }


}
