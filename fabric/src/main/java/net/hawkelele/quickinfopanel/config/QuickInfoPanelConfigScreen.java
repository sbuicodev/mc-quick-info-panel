package net.hawkelele.quickinfopanel.config;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.platform.Services;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.util.LinkedHashMap;

public class QuickInfoPanelConfigScreen extends Screen {
    private static final int BUTTON_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 20;
    private static final int ROW_SPACING = 24;

    private final Screen parent;
    private final Config config;

    private Button displayMainPanelButton;
    private Button displaySecondaryPanelButton;
    private Button layoutButton;
    private Button debugBoundsButton;
    private final LinkedHashMap<String, Button> panelButtons = new LinkedHashMap<>();

    public QuickInfoPanelConfigScreen(Screen parent) {
        super(Component.translatable("title." + Constants.MOD_ID + ".config"));
        this.parent = parent;
        this.config = copyOf(Config.read());
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();
        panelButtons.clear();

        int centerX = this.width / 2;
        int left = centerX - BUTTON_WIDTH / 2;
        int y = 48;

        displayMainPanelButton = addRenderableWidget(Button.builder(toggleLabel("settings." + Constants.MOD_ID + ".enable", config.displayMainPanel), button -> {
            config.displayMainPanel = !config.displayMainPanel;
            refreshMessages();
        }).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        y += ROW_SPACING;
        displaySecondaryPanelButton = addRenderableWidget(Button.builder(toggleLabel("settings." + Constants.MOD_ID + ".enable-alt-info", config.displaySecondaryPanel), button -> {
            config.displaySecondaryPanel = !config.displaySecondaryPanel;
            refreshMessages();
        }).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        y += ROW_SPACING;
        layoutButton = addRenderableWidget(Button.builder(layoutLabel(), button -> {
            config.layout = nextLayoutPreset().id();
            refreshMessages();
        }).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());

        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            y += ROW_SPACING;
            debugBoundsButton = addRenderableWidget(Button.builder(toggleLabel("settings." + Constants.MOD_ID + ".debug", config.debugBounds), button -> {
                config.debugBounds = !config.debugBounds;
                refreshMessages();
            }).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        }

        if (Config.isSingleElementHidingEnabled()) {
            y += ROW_SPACING * 2;
            for (String key : Config.PANEL_KEYS) {
                Button panelButton = addRenderableWidget(Button.builder(panelLabel(key), button -> {
                    config.panels.put(key, !config.panels.getOrDefault(key, true));
                    refreshMessages();
                }).bounds(left, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
                panelButtons.put(key, panelButton);
                y += ROW_SPACING;
            }
        }

        int footerY = this.height - 28;
        addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, button -> saveAndClose())
                .bounds(centerX - 102, footerY, 100, BUTTON_HEIGHT)
                .build());
        addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> onClose())
                .bounds(centerX + 2, footerY, 100, BUTTON_HEIGHT)
                .build());

        refreshMessages();
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        extractTransparentBackground(graphics);
        graphics.centeredText(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        graphics.text(this.font, Component.translatable("modmenu.quickinfopanel.categories.general"), this.width / 2 - BUTTON_WIDTH / 2, 36, 0xA0A0A0);
        if (Config.isSingleElementHidingEnabled()) {
            int panelHeaderY = Services.PLATFORM.isDevelopmentEnvironment() ? 144 : 120;
            graphics.text(this.font, Component.translatable("quickinfopanel.configuration.panels"), this.width / 2 - BUTTON_WIDTH / 2, panelHeaderY, 0xA0A0A0);
        }
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreenAndShow(parent);
        }
    }

    private void saveAndClose() {
        try {
            Config.write(config);
        } catch (IOException exception) {
            Constants.LOG.error("Failed to write config", exception);
        }
        onClose();
    }

    private void refreshMessages() {
        displayMainPanelButton.setMessage(toggleLabel("settings." + Constants.MOD_ID + ".enable", config.displayMainPanel));
        displaySecondaryPanelButton.setMessage(toggleLabel("settings." + Constants.MOD_ID + ".enable-alt-info", config.displaySecondaryPanel));
        layoutButton.setMessage(layoutLabel());
        if (debugBoundsButton != null) {
            debugBoundsButton.setMessage(toggleLabel("settings." + Constants.MOD_ID + ".debug", config.debugBounds));
        }
        panelButtons.forEach((key, button) -> button.setMessage(panelLabel(key)));
    }

    private Component layoutLabel() {
        return CommonComponents.optionNameValue(
                Component.translatable("position." + Constants.MOD_ID + ".label"),
                LayoutPreset.fromId(config.layout).getTranslatedName()
        );
    }

    private Component panelLabel(String key) {
        return CommonComponents.optionNameValue(
                Component.translatable("quickinfopanel.configuration.panels." + key),
                CommonComponents.optionStatus(config.panels.getOrDefault(key, true))
        );
    }

    private Component toggleLabel(String translationKey, boolean value) {
        return CommonComponents.optionNameValue(Component.translatable(translationKey), CommonComponents.optionStatus(value));
    }

    private LayoutPreset nextLayoutPreset() {
        LayoutPreset[] values = LayoutPreset.values();
        LayoutPreset current = LayoutPreset.fromId(config.layout);
        return values[(current.ordinal() + 1) % values.length];
    }

    private static Config copyOf(Config source) {
        Config copy = new Config();
        copy.displayMainPanel = source.displayMainPanel;
        copy.displaySecondaryPanel = source.displaySecondaryPanel;
        copy.layout = source.layout;
        copy.debugBounds = source.debugBounds;
        copy.panels = new LinkedHashMap<>(source.panels);
        return copy;
    }
}
