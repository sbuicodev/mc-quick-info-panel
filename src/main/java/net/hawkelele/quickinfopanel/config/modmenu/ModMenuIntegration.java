package net.hawkelele.quickinfopanel.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.settings.GeneralSettings;
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
                    .setTitle(Component.translatable("title.quickinfopanel.config"));

            ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // displayPanel
            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings.quickinfopanel.enable"), settings.displayPanel)
                    .setDefaultValue(true) // Recommended: Used when user click "Reset"
                    .setTooltip(Component.translatable("settings.quickinfopanel.enable.description"))
                    .setSaveConsumer(newValue -> settings.displayPanel = newValue) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config

            general.addEntry(entryBuilder.startBooleanToggle(Component.translatable("settings.quickinfopanel.enable-alt-info"), settings.displayPanel)
                    .setDefaultValue(true) // Recommended: Used when user click "Reset"
                    .setTooltip(Component.translatable("settings.quickinfopanel.enable-alt-info.description"))
                    .setSaveConsumer(newValue -> settings.displayAlternateDimensionInfo = newValue) // Recommended: Called when user save the config
                    .build()); // Builds the option entry for cloth config

            // position (preset)
            general.addEntry(entryBuilder
                    .startSelector(
                            Component.translatable("position.quickinfopanel.label"),
                            new String[]{"default", "top-left", "top-right", "bottom-left", "bottom-right"},
                            settings.position.code != null ? settings.position.code : "default"
                    )
                    .setDefaultValue("default")
                    .setSaveConsumer(newValue -> settings.position = GeneralSettings.Position.preset(newValue))
                    .build()
            );

            builder.setSavingRunnable(() -> {
                Config.getInstance().write(settings);
            });

            return builder.build();
        };
    }
}
