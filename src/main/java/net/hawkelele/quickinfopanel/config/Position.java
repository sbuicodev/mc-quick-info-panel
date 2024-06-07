package net.hawkelele.quickinfopanel.config;

import net.minecraft.client.MinecraftClient;

public class Position {
    public int x = 0;
    public int y = 0;
    public boolean centered = true;
    public boolean moveWithInventory = true;
    public boolean hideWithActionbar = true;
    public boolean invertLines = false;

    public void applyPreset(String presetName) {
        switch (presetName) {
            case "top-left":
                x = 5;
                y = 5;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                break;
            case "top-right":
                x = MinecraftClient.getInstance().getWindow().getScaledWidth() - 150;
                y = 30;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                break;
            case "bottom-left":
                x = 5;
                y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 40;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                break;
            case "bottom-right":
                x = MinecraftClient.getInstance().getWindow().getScaledWidth() - 150;
                y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 40;
                centered = false;
                moveWithInventory = false;
                hideWithActionbar = false;
                invertLines = true;
                break;
            case "default":
            default:
                y = MinecraftClient.getInstance().getWindow().getScaledHeight() - 70 + (MinecraftClient.getInstance().textRenderer.fontHeight);
                centered = true;
                moveWithInventory = true;
                hideWithActionbar = true;
                invertLines = false;
        }
    }
}
