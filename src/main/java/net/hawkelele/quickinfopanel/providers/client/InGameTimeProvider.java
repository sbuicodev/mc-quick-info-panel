package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.TimeProvider;
import net.minecraft.client.Minecraft;

public class InGameTimeProvider implements TimeProvider {
    public static final long TICKS_PER_GAME_HOUR = 1000;
    private final Minecraft client = Minecraft.getInstance();

    @Override
    public long getCurrentTicks() {
        if (client.level == null) throw new IllegalStateException("Tried to access in-game time before world was loaded");

        return client.level.getOverworldClockTime();
    }

    @Override
    public long getTicksPerHour() {
        return TICKS_PER_GAME_HOUR;
    }
}
