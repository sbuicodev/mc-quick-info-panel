package net.hawkelele.quickinfopanel.config.settings;

public enum PositionPresets {
    DEFAULT(new GeneralSettings.Position("default", 0, 71, true, true, true, false, false, true));

    public final GeneralSettings.Position position;


    PositionPresets(GeneralSettings.Position position) {
        this.position = position;
    }
}
