package net.hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.TimeProvider;

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

        return String.format("%02d:%02d", time[0], time[1]);
    }

    public String getDayPeriod() {
        int currentHour = getCurrentTime()[0];
        return currentHour < 6 || currentHour >= 18 ? "night" : "day";
    }

    public String getIconPath() {
        return "texture/gui/clock/clock_" + getDayPeriod() + ".png";
    }
}
