package net.hawkelele.quickinfopanel.config.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.settings.GeneralSettings;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Create a copy of the settings to store any temporary changes
            GeneralSettings settings = (GeneralSettings) Config.getInstance().settings().clone();

            ConfigBuilder builder = ConfigBuilder
                    .create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("Quick Info Panel"));

            ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            // displayPanel
            general.addEntry(entryBuilder.startBooleanToggle(Text.literal("Enable Quick Info Panel"), settings.displayPanel)
                                         .setDefaultValue(true) // Recommended: Used when user click "Reset"
                                         .setTooltip(Text.literal("Display quick access info about the player's coordinates and time above the inventory")) // Optional: Shown when the user hover over this option
                                         .setSaveConsumer(newValue -> settings.displayPanel = newValue) // Recommended: Called when user save the config
                                         .build()); // Builds the option entry for cloth config

            // position (preset)
            general.addEntry(entryBuilder
                    .startSelector(
                            Text.literal("Position (preset)"),
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
