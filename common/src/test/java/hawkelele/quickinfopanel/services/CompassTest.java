package hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.DirectionProvider;
import net.hawkelele.quickinfopanel.services.Compass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompassTest {
    private static class MockDirectionProvider implements DirectionProvider {
        public final static String NORTH = "north";
        public final static String EAST = "east";
        public final static String WEST = "west";
        public final static String SOUTH = "south";

        private String currentDirection = NORTH;

        public void setCurrentDirection(String currentDirection) {
            this.currentDirection = currentDirection;
        }

        @Override
        public String getDirection() {
            return currentDirection;
        }
    }

    @Test
    void testGetCurrentFacingCardinalDirection() {
        MockDirectionProvider directionProvider = new MockDirectionProvider();
        Compass compass = new Compass(directionProvider);

        directionProvider.setCurrentDirection(MockDirectionProvider.NORTH);
        Assertions.assertEquals("N", compass.getCurrentFacingCardinalDirection());

        directionProvider.setCurrentDirection(MockDirectionProvider.EAST);
        Assertions.assertEquals("E", compass.getCurrentFacingCardinalDirection());

        directionProvider.setCurrentDirection(MockDirectionProvider.WEST);
        Assertions.assertEquals("W", compass.getCurrentFacingCardinalDirection());

        directionProvider.setCurrentDirection(MockDirectionProvider.SOUTH);
        Assertions.assertEquals("S", compass.getCurrentFacingCardinalDirection());

        directionProvider.setCurrentDirection("unknown direction");
        Assertions.assertEquals("?", compass.getCurrentFacingCardinalDirection());

    }
}
