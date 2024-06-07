package net.hawkelele.quickinfopanel.config;

public class Position {
    public int x = 0;
    public int y = 0;
    public boolean useDefault = false;
    public boolean invertLines = false;

    public void applyPreset(String presetName) {
        switch (presetName) {
            case "top-left":
                x = 5;
                y = 5;
                useDefault = false;
                invertLines = true;
                break;
            case "default":
            default:
                useDefault = true;
                invertLines = false;
        }
    }
}
