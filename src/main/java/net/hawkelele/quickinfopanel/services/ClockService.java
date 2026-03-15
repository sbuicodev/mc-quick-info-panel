package net.hawkelele.quickinfopanel.services;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.StringUtils;

public class ClockService {
    private static final Minecraft client = Minecraft.getInstance();

    private static int[] getCurrentTime() {
        assert client.level != null;
        long ticks = client.level.getDayTime();
        float hours = (((float) ticks / 1000) + 6) % 24;
        float minutes = (hours * 60) % 60;

        return new int[]{(int) Math.floor(hours), (int) Math.floor(minutes)};
    }

    /**
     * Translates in-game ticks to a human-readable 24h clock format
     *
     * @return The current time of the day in a 24h hh:mm format
     */
    private static String getCurrentClock() {
        int[] time = getCurrentTime();

        return String.format(" %s:%s",
                StringUtils.leftPad(String.valueOf(time[0]), 2, "0"),
                StringUtils.leftPad(String.valueOf(time[1]), 2, "0")
        );
    }

    public static String string() {
        return getCurrentClock();
    }

    public static String icon() {
        return "texture/gui/clock/clock_" + (getCurrentTime()[0] >= 18 ? "night" : "day") + ".png";
    }
}
