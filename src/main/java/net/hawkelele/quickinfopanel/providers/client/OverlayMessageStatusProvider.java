package net.hawkelele.quickinfopanel.providers.client;

import net.minecraft.client.Minecraft;

public class OverlayMessageStatusProvider {
    private static boolean displaying = false;
    private static int remainingTicks = -1;



    public static boolean isDisplaying() {
        return displaying;
    }

    public static void setDisplaying(boolean status) {
        displaying = status;
    }

    public static void setTimer(int ticks) {
        remainingTicks = ticks;
    }

    public static void reset() {
        displaying = false;
        remainingTicks = -1;
    }

    public static void tick(Minecraft client) {
        if (remainingTicks > 0) {
            remainingTicks--;
        } else {
            reset();
        }
    }

}
