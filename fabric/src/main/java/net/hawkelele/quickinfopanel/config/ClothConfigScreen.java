package net.hawkelele.quickinfopanel.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.platform.Services;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.io.IOException;

public class ClothConfigScreen {
    public Screen create(Screen parent) {
        // Create a copy of the settings to store any temporary changes
        Config config = Config.read();

        ConfigBuilder builder = ConfigBuilder
                .create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("title." + Constants.MOD_ID + ".config"));

        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // displayPanel
        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + Constants.MOD_ID + ".enable"), config.displayMainPanel)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Component.translatable("settings." + Constants.MOD_ID + ".enable.description"))
                .setSaveConsumer(newValue -> config.displayMainPanel = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + Constants.MOD_ID + ".enable-alt-info"), config.displaySecondaryPanel)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Component.translatable("settings." + Constants.MOD_ID + ".enable-alt-info.description"))
                .setSaveConsumer(newValue -> config.displaySecondaryPanel = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        // Specific elements toggles
        ConfigCategory widgets = builder.getOrCreateCategory(Component.literal("Widgets"));
        config.panels.forEach((key, value) -> {
            widgets.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + Constants.MOD_ID + ".enable-" + key), value)
                    .setDefaultValue(true) // Recommended: Used when user click "Reset"
                    .setTooltip(Component.translatable("settings." + Constants.MOD_ID + ".enable-" + key + ".description"))
                    .setSaveConsumer(newValue -> config.panels.put(key, newValue)) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config
        });

        // position (preset)
        general.addEntry(entryBuilder
                .startSelector(
                        Component.translatable("position." + Constants.MOD_ID + ".label"),
                        LayoutRegistry.list(),
                        config.layout != null ? config.layout : "default"
                )
                .setDefaultValue("default")
                .setSaveConsumer(newValue -> config.layout = (newValue))
                .build()
        );

        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + Constants.MOD_ID + ".debug"), config.debugBounds)
                    .setDefaultValue(false)
                    .setTooltip(Component.literal("Render element bounds for debugging"))
                    .setSaveConsumer(newValue -> config.debugBounds = newValue)
                    .build());
        }

        builder.setSavingRunnable(() -> {
            try {
                Config.write(config);
            } catch (IOException e) {
                Constants.LOG.error("Failed to write config", e);
            }
        });

        return builder.build();
    }

}
