package hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.TimeProvider;
import net.hawkelele.quickinfopanel.services.Clock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {
    private static class MockTimeProvider implements TimeProvider {
        private final static long DAY = 1000;
        private final static long NOON = 6000;
        private final static long SUNSET = 12000;
        private final static long NIGHT = 13000;
        private final static long MIDNIGHT = 18000;
        private final static long SUNRISE = 23000;

        private final static long TICKS_PER_HOUR = 1000;
        private long currentTicks = 0;

        public void setCurrentTicks(long currentTicks) {
            this.currentTicks = currentTicks;
        }

        @Override
        public long getCurrentTicks() {
            return currentTicks;
        }

        @Override
        public long getTicksPerHour() {
            return TICKS_PER_HOUR;
        }
    }

    @Test
    void testGetCurrentTime() {
        MockTimeProvider timeProvider = new MockTimeProvider();
        Clock clock = new Clock(timeProvider);

        timeProvider.setCurrentTicks(MockTimeProvider.DAY);
        Assertions.assertArrayEquals(new int[]{7, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(MockTimeProvider.NOON);
        Assertions.assertArrayEquals(new int[]{12, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(MockTimeProvider.SUNSET);
        Assertions.assertArrayEquals(new int[]{18, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(MockTimeProvider.NIGHT);
        Assertions.assertArrayEquals(new int[]{19, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(MockTimeProvider.MIDNIGHT);
        Assertions.assertArrayEquals(new int[]{0, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(MockTimeProvider.SUNRISE);
        Assertions.assertArrayEquals(new int[]{5, 0}, clock.getCurrentTime());

        timeProvider.setCurrentTicks(12542);
        Assertions.assertArrayEquals(new int[]{18, 32}, clock.getCurrentTime());
    }

    @Test
    void testGetCurrentTimeAsClockString() {
        MockTimeProvider timeProvider = new MockTimeProvider();
        Clock clock = new Clock(timeProvider);

        timeProvider.setCurrentTicks(MockTimeProvider.DAY);
        Assertions.assertEquals("07:00", clock.getCurrentTimeAsClockString());

        timeProvider.setCurrentTicks(11834);
        Assertions.assertEquals("17:50", clock.getCurrentTimeAsClockString());
    }

    @Test
    void testGetDayPeriod() {
        MockTimeProvider timeProvider = new MockTimeProvider();
        Clock clock = new Clock(timeProvider);

        timeProvider.setCurrentTicks(MockTimeProvider.DAY);
        Assertions.assertEquals("day", clock.getDayPeriod());

        timeProvider.setCurrentTicks(MockTimeProvider.NIGHT);
        Assertions.assertEquals("night", clock.getDayPeriod());
    }

    @Test
    void testGetIconPath() {
        MockTimeProvider timeProvider = new MockTimeProvider();
        Clock clock = new Clock(timeProvider);

        timeProvider.setCurrentTicks(MockTimeProvider.DAY);
        Assertions.assertEquals("texture/gui/clock/clock_day.png", clock.getIconPath());

        timeProvider.setCurrentTicks(MockTimeProvider.NIGHT);
        Assertions.assertEquals("texture/gui/clock/clock_night.png", clock.getIconPath());

        timeProvider.setCurrentTicks(MockTimeProvider.MIDNIGHT);
        Assertions.assertEquals("texture/gui/clock/clock_night.png", clock.getIconPath());

        timeProvider.setCurrentTicks(MockTimeProvider.SUNRISE);
        Assertions.assertEquals("texture/gui/clock/clock_night.png", clock.getIconPath());

        timeProvider.setCurrentTicks(MockTimeProvider.SUNSET);
        Assertions.assertEquals("texture/gui/clock/clock_night.png", clock.getIconPath());

        timeProvider.setCurrentTicks(MockTimeProvider.NOON);
        Assertions.assertEquals("texture/gui/clock/clock_day.png", clock.getIconPath());


    }
}
