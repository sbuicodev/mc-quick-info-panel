package net.hawkelele.quickinfopanel.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hawkelele.quickinfopanel.QuickInfoPanel;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.settings.GeneralSettings;
import net.hawkelele.quickinfopanel.registry.LayoutRegistry;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Create a copy of the settings to store any temporary changes
            GeneralSettings settings = (GeneralSettings) Config.getInstance().settings().clone();

            ConfigBuilder builder = ConfigBuilder
                    .create()
                    .setParentScreen(parent)
                    .setTitle(Component.translatable("title." + QuickInfoPanel.MOD_ID + ".config"));

            ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // displayPanel
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + QuickInfoPanel.MOD_ID + ".enable"), settings.displayMainPanel)
                    .setDefaultValue(true) // Recommended: Used when user click "Reset"
                    .setTooltip(Component.translatable("settings." + QuickInfoPanel.MOD_ID + ".enable.description"))
                    .setSaveConsumer(newValue -> settings.displayMainPanel = newValue) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config

            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings." + QuickInfoPanel.MOD_ID + ".enable-alt-info"), settings.displayMainPanel)
                    .setDefaultValue(true) // Recommended: Used when user click "Reset"
                    .setTooltip(Component.translatable("settings." + QuickInfoPanel.MOD_ID + ".enable-alt-info.description"))
                    .setSaveConsumer(newValue -> settings.displaySecondaryPanel = newValue) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config

            // position (preset)
            general.addEntry(entryBuilder
                    .startSelector(
                            Component.translatable("position." + QuickInfoPanel.MOD_ID + ".label"),
                            LayoutRegistry.list(),
                            settings.layout != null ? settings.layout : "default"
                    )
                    .setDefaultValue("default")
                    .setSaveConsumer(newValue -> settings.layout = (newValue))
                    .build()
            );

            builder.setSavingRunnable(() -> {
                Config.getInstance().write(settings);
            });

            return builder.build();
        };
    }
}
