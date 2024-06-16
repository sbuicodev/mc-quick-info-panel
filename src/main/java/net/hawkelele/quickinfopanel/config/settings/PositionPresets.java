package net.hawkelele.quickinfopanel.config.settings;

public enum PositionPresets {
    DEFAULT(new GeneralSettings.Position("default", 0, 71, true, true, true, false, false, true)),
    TOP_LEFT(new GeneralSettings.Position("top-left", 5, 5, false, false, false, true, false, false)),
    TOP_RIGHT(new GeneralSettings.Position("top-right", 150, 30, false, false, false, true, true, false)),
    BOTTOM_LEFT(new GeneralSettings.Position("bottom-left", 5, 40, false, false, false, false, false, true)),
    BOTTOM_RIGHT(new GeneralSettings.Position("bottom-right", 150, 40, false, false, false, false, true, true));

    public final GeneralSettings.Position position;


    PositionPresets(GeneralSettings.Position position) {
        this.position = position;
    }
}
