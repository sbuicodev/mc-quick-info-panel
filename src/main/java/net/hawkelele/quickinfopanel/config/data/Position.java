package net.hawkelele.quickinfopanel.config.data;

public class Position {
    public int x = 0;
    public int y = 0;
    public boolean centered = true;
    public boolean moveWithInventory = true;
    public boolean hideWithActionbar = true;
    public boolean invertLines = false;
    public boolean invertedX = false;
    public boolean invertedY = true;


    public void applyPreset(String presetName) {
        switch (presetName) {
            case "top-left":
                x = 5;
                y = 5;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                invertedX = false;
                invertedY = false;
                break;
            case "top-right":
                x = 150;
                y = 30;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                invertedX = true;
                invertedY = false;
                break;
            case "bottom-left":
                x = 5;
                y = 40;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                invertedX = false;
                invertedY = true;
                break;
            case "bottom-right":
                x = 150;
                y = 40;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                invertedX = true;
                invertedY = true;
                break;
            case "default":
            default:
                y = 71;
                centered = true;
                moveWithInventory = true;
                hideWithActionbar = true;
                invertLines = false;
                invertedX = false;
                invertedY = true;
        }
    }
}
