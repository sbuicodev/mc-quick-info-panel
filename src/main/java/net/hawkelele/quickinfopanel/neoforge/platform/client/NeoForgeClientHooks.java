package net.hawkelele.quickinfopanel.neoforge.platform.client;

//? if neoforge {
/*import net.neoforged.fml.ModContainer;
//? if >=1.21 {
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
//?} else {
/^import net.hawkelele.quickinfopanel.config.QuickInfoPanelConfigScreen;
^///?}
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class NeoForgeClientHooks {
    public static void registerConfigScreen(ModContainer modContainer) {
        //? if >=1.21 {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new ConfigurationScreen(modContainer, parent));
        //?} else {
        /^// NeoForge's own config screen arrives in 1.21
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> new QuickInfoPanelConfigScreen(parent));
        ^///?}
    }
}
*///?}
