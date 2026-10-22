package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.platform.services.IConfigHelper;
import net.hawkelele.quickinfopanel.platform.services.IConfigStore;
import net.hawkelele.quickinfopanel.platform.services.ICommandsHelper;
import net.hawkelele.quickinfopanel.platform.services.IHudHelper;
import net.hawkelele.quickinfopanel.platform.services.IKeybindsHelper;
import net.hawkelele.quickinfopanel.platform.services.IPlatformHelper;
//? if fabric {
import net.hawkelele.quickinfopanel.fabric.platform.FabricCommandsHelper;
import net.hawkelele.quickinfopanel.fabric.platform.FabricConfigHelper;
import net.hawkelele.quickinfopanel.fabric.platform.FabricHudHelper;
import net.hawkelele.quickinfopanel.fabric.platform.FabricKeybindsHelper;
import net.hawkelele.quickinfopanel.fabric.platform.FabricPlatformHelper;
//?} else {
/*import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeCommandsHelper;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeConfigHelper;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeHudHelper;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgeKeybindsHelper;
import net.hawkelele.quickinfopanel.neoforge.platform.NeoForgePlatformHelper;
*///?}

// Each jar contains a single loader, so its implementations are picked at build time
public class Services {
    //? if fabric {
    public static final IPlatformHelper PLATFORM = new FabricPlatformHelper();
    public static final IHudHelper HUD = new FabricHudHelper();
    public static final IKeybindsHelper KEYBINDS = new FabricKeybindsHelper();
    public static final ICommandsHelper COMMANDS = new FabricCommandsHelper();
    public static final IConfigHelper CONFIG_HELPER = new FabricConfigHelper();
    public static final IConfigStore CONFIG = new FabricConfigHelper();
    //?} else {
    /*public static final IPlatformHelper PLATFORM = new NeoForgePlatformHelper();
    public static final IHudHelper HUD = new NeoForgeHudHelper();
    public static final IKeybindsHelper KEYBINDS = new NeoForgeKeybindsHelper();
    public static final ICommandsHelper COMMANDS = new NeoForgeCommandsHelper();
    public static final IConfigHelper CONFIG_HELPER = new NeoForgeConfigHelper();
    public static final IConfigStore CONFIG = new NeoForgeConfigHelper();
    *///?}
}
