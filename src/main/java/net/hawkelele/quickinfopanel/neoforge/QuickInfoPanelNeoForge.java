package net.hawkelele.quickinfopanel.neoforge;

//? if neoforge {
/*import net.hawkelele.quickinfopanel.Common;
import net.hawkelele.quickinfopanel.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
//? if >=1.21 {
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
//?} else {
/^import net.hawkelele.quickinfopanel.config.QuickInfoPanelConfigScreen;
^///?}
//? if >=1.20.5 {
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?} else {
/^import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler;
^///?}
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeConfigHelper;

@Mod(Constants.MOD_ID)
public class QuickInfoPanelNeoForge {

    //? if >=1.20.5 {
    public QuickInfoPanelNeoForge(IEventBus modBus, ModContainer modContainer) {
    //?} else {
    /^// The mod container can only be injected from 20.5; before, registration goes through ModLoadingContext
    public QuickInfoPanelNeoForge(IEventBus modBus) {
    ^///?}
        Constants.LOG.info("Hello NeoForge world!");
        //? if >=1.20.5 {
        modContainer.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfigHelper.spec());
        //?} else {
        /^ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NeoForgeConfigHelper.spec());
        ^///?}
        //? if >=1.21 {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new ConfigurationScreen(modContainer, parent));
        //?} else if >=1.20.5 {
        /^// NeoForge's own config screen arrives in 1.21
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new QuickInfoPanelConfigScreen(parent));
        ^///?} else {
        /^ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, parent) -> new QuickInfoPanelConfigScreen(parent)));
        ^///?}
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
