package net.hawkelele.quickinfopanel.config;

import net.hawkelele.quickinfopanel.Constants;
import net.minecraft.network.chat.Component;

public enum LayoutPreset {
    DEFAULT("default"),
    TOP_LEFT("top-left"),
    TOP_RIGHT("top-right"),
    BOTTOM_LEFT("bottom-left"),
    BOTTOM_RIGHT("bottom-right");

    private final String id;

    LayoutPreset(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public static LayoutPreset fromId(String id) {
        for (LayoutPreset preset : values()) {
            if (preset.id.equals(id)) {
                return preset;
            }
        }
        return DEFAULT;
    }

    public Component getTranslatedName() {
        return Component.translatable("position." + Constants.MOD_ID + "." + id);
    }
}
