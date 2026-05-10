package net.hawkelele.quickinfopanel;

import com.mojang.blaze3d.platform.InputConstants;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.handlers.ToggleLayoutDebugHandler;
import net.hawkelele.quickinfopanel.platform.Services;
import net.hawkelele.quickinfopanel.registry.KeybindsRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as NeoForge events
// however it will be compatible with all supported mod loaders.
public class Common {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        Services.HUD.registerHudRenderCallback();

        KeybindsRegistry.registerKeybinds();

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
        if (Services.PLATFORM.isModLoaded("quickinfopanel")) {
        }
    }
}