package net.hawkelele.quickinfopanel.neoforge;

//? if neoforge {
/*import net.hawkelele.quickinfopanel.Common;
import net.hawkelele.quickinfopanel.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeConfigHelper;

@Mod(Constants.MOD_ID)
public class QuickInfoPanelNeoForge {

    public QuickInfoPanelNeoForge(IEventBus modBus, ModContainer modContainer) {
        Constants.LOG.info("Hello NeoForge world!");
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfigHelper.spec());
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new ConfigurationScreen(modContainer, parent));
        modBus.addListener(this::onConfigEvent);
        Common.init();
    }

    private void onConfigEvent(ModConfigEvent event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            Config.refresh(NeoForgeConfigHelper.snapshot());
        }
    }
}
*///?}
