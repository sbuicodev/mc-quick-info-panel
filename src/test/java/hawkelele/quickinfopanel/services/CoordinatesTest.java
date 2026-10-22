package hawkelele.quickinfopanel.services;

import net.hawkelele.quickinfopanel.providers.DimensionProvider;
import net.hawkelele.quickinfopanel.providers.PositionProvider;
import net.hawkelele.quickinfopanel.services.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {
    private static class MockPositionProvider implements PositionProvider {
        private double[] position = new double[]{0, 0, 0};

        public void setPosition(double[] position) {
            this.position = position;
        }

        @Override
        public double[] getPosition() {
            return position;
        }
    }

    private static class MockDimensionProvider implements DimensionProvider {
        public static final String OVERWORLD = "minecraft:overworld";
        public static final String NETHER = "minecraft:the_nether";
        public static final String END = "minecraft:the_end";

        private String dimension = OVERWORLD;

        public void setDimension(String dimension) {
            this.dimension = dimension;
        }

        @Override
        public String getDimension() {
            return dimension;
        }
    }

    @Test
    void testCoordinatesAndDimensions() {
        MockPositionProvider positionProvider = new MockPositionProvider();
        MockDimensionProvider dimensionProvider = new MockDimensionProvider();

        Coordinates coordinates = new Coordinates(positionProvider, dimensionProvider);

        positionProvider.setPosition(new double[]{100, 200, 300});
        dimensionProvider.setDimension(MockDimensionProvider.OVERWORLD);
        Assertions.assertArrayEquals(new int[]{100, 200, 300}, coordinates.getCurrentPosition());

        positionProvider.setPosition(new double[]{10, 20, 30});
        dimensionProvider.setDimension(MockDimensionProvider.NETHER);
        Assertions.assertEquals(MockDimensionProvider.NETHER, coordinates.getCurrentDimension());
        Assertions.assertArrayEquals(new int[]{10, 20, 30}, coordinates.getCurrentPosition());
        Assertions.assertArrayEquals(new int[]{80, 20, 240}, coordinates.getOppositeDimensionPosition());

        // Test rounding
        positionProvider.setPosition(new double[]{100.5, 200.5, 300.5});
        Assertions.assertArrayEquals(new int[]{100, 200, 300}, coordinates.getCurrentPosition());

        // Test END dimension
        dimensionProvider.setDimension(MockDimensionProvider.END);
        Assertions.assertEquals(MockDimensionProvider.END, coordinates.getCurrentDimension());
        Assertions.assertArrayEquals(new int[]{100, 200, 300}, coordinates.getCurrentPosition());
        Assertions.assertTrue(coordinates.hasNoOppositeDimension());
        Assertions.assertArrayEquals(new int[]{0, 0, 0}, coordinates.getOppositeDimensionPosition());
    }
}
