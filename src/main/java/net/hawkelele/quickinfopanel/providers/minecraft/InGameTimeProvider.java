package net.hawkelele.quickinfopanel.providers.minecraft;

import net.hawkelele.quickinfopanel.providers.TimeProvider;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;

public class InGameTimeProvider implements TimeProvider {
    public static final long TICKS_PER_GAME_HOUR = 1000;

    private final Minecraft client = Minecraft.getInstance();

    public long getCurrentTicks() {
        if (client.level == null) throw new IllegalStateException("Tried to access in-game time before world was loaded");

        return client.level.getOverworldClockTime();
    }

    public long getTicksPerHour() {
        return TICKS_PER_GAME_HOUR;
    }
}
