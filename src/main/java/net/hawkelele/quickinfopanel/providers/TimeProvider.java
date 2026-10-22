package net.hawkelele.quickinfopanel.providers;

public interface TimeProvider {
    long getCurrentTicks();
    long getTicksPerHour();
}
