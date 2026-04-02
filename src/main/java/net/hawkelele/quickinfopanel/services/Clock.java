package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.TimeProvider;
import org.apache.commons.lang3.StringUtils;

public class Clock {
    private final TimeProvider timeProvider;

    public Clock(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public int[] getCurrentTime() {
        long ticks = timeProvider.getCurrentTicks();

        float hours = (((float) ticks / timeProvider.getTicksPerHour()) + 6) % 24;
        float minutes = ((float) ticks / ((float)timeProvider.getTicksPerHour() / 60)) % 60;

        return new int[]{(int) Math.floor(hours), (int) Math.floor(minutes)};
    }

    public String getCurrentTimeAsClockString() {
        int[] time = getCurrentTime();

        return String.format("%s:%s",
                StringUtils.leftPad(String.valueOf(time[0]), 2, "0"),
                StringUtils.leftPad(String.valueOf(time[1]), 2, "0")
        );
    }

    public String getDayPeriod() {
        int currentHour = getCurrentTime()[0];
        return currentHour < 6 || currentHour >= 18 ? "night" : "day";
    }

    public String getIconPath() {
        return "texture/gui/clock/clock_" + getDayPeriod() + ".png";
    }
}
