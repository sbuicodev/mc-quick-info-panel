package net.hawkelele.quickinfopanel.handlers;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.config.Config;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class ToggleLayoutDebugHandler implements IClientEventHandler {
    @Override
    public void accept(Minecraft minecraft) {
        try {
            Config.write(config -> {
                if (minecraft == null || minecraft.player == null) {
                    return config;
                }

                config.debugBounds = !config.debugBounds;
                return config;
            });
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config", e);
        }
    }
}
