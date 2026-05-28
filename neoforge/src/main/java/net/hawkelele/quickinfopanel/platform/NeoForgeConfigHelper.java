package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.LayoutPreset;
import net.hawkelele.quickinfopanel.platform.Services;
import net.hawkelele.quickinfopanel.platform.services.IConfigHelper;
import net.hawkelele.quickinfopanel.platform.services.IConfigStore;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.TranslatableEnum;

import org.apache.commons.lang3.tuple.Pair;

public class NeoForgeConfigHelper implements IConfigHelper, IConfigStore {
    private static final ModConfigSpec SPEC;
    private static final NeoForgeConfig BACKING;

    static {
        Pair<NeoForgeConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(NeoForgeConfig::new);
        BACKING = pair.getLeft();
        SPEC = pair.getRight();
    }

    @Override
    public void registerConfigScreen() {
        // NeoForge registers the config screen from the mod constructor where the ModContainer is available.
    }

    @Override
    public Config read() {
        return snapshot();
    }

    @Override
    public void save(Config config) {
        BACKING.fromConfig(config);
        SPEC.save();
    }

    public static ModConfigSpec spec() {
        return SPEC;
    }

    public static Config snapshot() {
        return BACKING.toConfig();
    }

    private static class NeoForgeConfig {
        private final ModConfigSpec.ConfigValue<Boolean> displayMainPanel;
        private final ModConfigSpec.ConfigValue<Boolean> displaySecondaryPanel;
        private final ModConfigSpec.EnumValue<NeoForgeLayoutPreset> layout;
        private final ModConfigSpec.ConfigValue<Boolean> debugBounds;
        private final ModConfigSpec.ConfigValue<Boolean> coordinates;
        private final ModConfigSpec.ConfigValue<Boolean> compass;
        private final ModConfigSpec.ConfigValue<Boolean> clock;
        private final ModConfigSpec.ConfigValue<Boolean> opposite;
        private final ModConfigSpec.ConfigValue<Boolean> biome;
        private final ModConfigSpec.ConfigValue<Boolean> weather;

        private NeoForgeConfig(ModConfigSpec.Builder builder) {
            displayMainPanel = builder.comment("Display the main panel")
                    .translation("settings." + Constants.MOD_ID + ".enable")
                    .define("displayMainPanel", true);
            displaySecondaryPanel = builder.comment("Display the secondary panel")
                    .translation("settings." + Constants.MOD_ID + ".enable-alt-info")
                    .define("displaySecondaryPanel", true);
            layout = builder.comment("Selected layout. Bottom anchored layouts can overlap with the HUD if the window is too small or GUI scaling is too large.")
                    .translation("position." + Constants.MOD_ID + ".label")
                    .defineEnum("layout", NeoForgeLayoutPreset.DEFAULT);

            builder.push("panels");
            coordinates = builder.comment("Display the coordinates")
                    .translation("settings." + Constants.MOD_ID + ".panels.coordinates")
                    .define("coordinates", true);
            compass = builder.comment("Display the compass")
                    .translation("settings." + Constants.MOD_ID + ".panels.compass")
                    .define("compass", true);
            clock = builder.comment("Display the current time")
                    .translation("settings." + Constants.MOD_ID + ".panels.time")
                    .define("clock", true);
            opposite = builder.comment("Display opposite coordinates")
                    .translation("settings." + Constants.MOD_ID + ".panels.opposite")
                    .define("opposite", true);
            biome = builder.comment("Display the current biome")
                    .translation("settings." + Constants.MOD_ID + ".panels.biome")
                    .define("biome", true);
            weather = builder.comment("Display the weather")
                    .translation("settings." + Constants.MOD_ID + ".panels.weather")
                    .define("weather", true);
            builder.pop();

            if (Services.PLATFORM.isDevelopmentEnvironment()) {
                debugBounds = builder.comment("Render debug bounds")
                        .translation("settings." + Constants.MOD_ID + ".debug")
                        .define("debugBounds", false);
            } else {
                debugBounds = builder.define("debugBounds", false);
            }
        }

        private Config toConfig() {
            Config config = new Config();
            config.displayMainPanel = displayMainPanel.get();
            config.displaySecondaryPanel = displaySecondaryPanel.get();
            config.layout = layout.get().preset.id();
            config.debugBounds = debugBounds.get();
            config.panels.put("coordinates", coordinates.get());
            config.panels.put("compass", compass.get());
            config.panels.put("clock", clock.get());
            config.panels.put("opposite", opposite.get());
            config.panels.put("biome", biome.get());
            config.panels.put("weather", weather.get());
            return config;
        }

        private void fromConfig(Config config) {
            displayMainPanel.set(config.displayMainPanel);
            displaySecondaryPanel.set(config.displaySecondaryPanel);
            layout.set(NeoForgeLayoutPreset.fromPreset(LayoutPreset.fromId(config.layout)));
            debugBounds.set(config.debugBounds);
            coordinates.set(config.panels.getOrDefault("coordinates", true));
            compass.set(config.panels.getOrDefault("compass", true));
            clock.set(config.panels.getOrDefault("clock", true));
            opposite.set(config.panels.getOrDefault("opposite", true));
            biome.set(config.panels.getOrDefault("biome", true));
            weather.set(config.panels.getOrDefault("weather", true));
        }
    }

    private enum NeoForgeLayoutPreset implements TranslatableEnum {
        DEFAULT(LayoutPreset.DEFAULT),
        TOP_LEFT(LayoutPreset.TOP_LEFT),
        TOP_RIGHT(LayoutPreset.TOP_RIGHT),
        BOTTOM_LEFT(LayoutPreset.BOTTOM_LEFT),
        BOTTOM_RIGHT(LayoutPreset.BOTTOM_RIGHT);

        private final LayoutPreset preset;

        NeoForgeLayoutPreset(LayoutPreset preset) {
            this.preset = preset;
        }

        static NeoForgeLayoutPreset fromPreset(LayoutPreset preset) {
            for (NeoForgeLayoutPreset value : values()) {
                if (value.preset == preset) {
                    return value;
                }
            }
            return DEFAULT;
        }

        @Override
        public net.minecraft.network.chat.Component getTranslatedName() {
            return preset.getTranslatedName();
        }
    }
}
