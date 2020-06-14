package wills.interpolation.helper;

import org.junit.jupiter.api.Test;
import wills.interpolation.model.Point;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataProcessingHelperTest {

    private final DataProcessingHelper helper = new DataProcessingHelper();

    private final String[][] example = new String[][]{
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
    };

    @Test
    public void shouldGetAdjacentValuesFromUpperLeft() {
        Set<Point> points = helper.getAdjacentPoints(0, 0, example);

        assertEquals(2, points.size());
        assertTrue(points.contains(new Point(0, 1)));
        assertTrue(points.contains(new Point(1, 0)));
    }

    @Test
    public void shouldGetAdjacentValuesFromUpperMiddle() {
        Set<Point> points = helper.getAdjacentPoints(0, 1, example);

        assertEquals(3, points.size());
        assertTrue(points.contains(new Point(0, 0)));
        assertTrue(points.contains(new Point(0, 2)));
        assertTrue(points.contains(new Point(1, 1)));
    }

    @Test
    public void shouldGetAdjacentValuesFromUpperRight() {
        Set<Point> points = helper.getAdjacentPoints(0, 2, example);

        assertEquals(2, points.size());
        assertTrue(points.contains(new Point(0, 1)));
        assertTrue(points.contains(new Point(1, 2)));
    }

    @Test
    public void shouldGetAdjacentValuesFromMiddleLeft() {
        Set<Point> points = helper.getAdjacentPoints(1, 0, example);

        assertEquals(3, points.size());
        assertTrue(points.contains(new Point(0, 0)));
        assertTrue(points.contains(new Point(1, 1)));
        assertTrue(points.contains(new Point(2, 0)));
    }

    @Test
    public void shouldGetAdjacentValuesFromCentre() {
        Set<Point> points = helper.getAdjacentPoints(1, 1, example);

        assertEquals(4, points.size());
        assertTrue(points.contains(new Point(0, 1)));
        assertTrue(points.contains(new Point(1, 0)));
        assertTrue(points.contains(new Point(1, 2)));
        assertTrue(points.contains(new Point(2, 1)));
    }

    @Test
    public void shouldGetAdjacentValuesFromMiddleRight() {
        Set<Point> points = helper.getAdjacentPoints(1, 2, example);

        assertEquals(3, points.size());
        assertTrue(points.contains(new Point(0, 2)));
        assertTrue(points.contains(new Point(1, 1)));
        assertTrue(points.contains(new Point(2, 2)));
    }

    @Test
    public void shouldGetAdjacentValuesFromLowerLeft() {
        Set<Point> points = helper.getAdjacentPoints(2, 0, example);

        assertEquals(2, points.size());
        assertTrue(points.contains(new Point(1, 0)));
        assertTrue(points.contains(new Point(2, 1)));
    }

    @Test
    public void shouldGetAdjacentValuesFromLowerMiddle() {
        Set<Point> points = helper.getAdjacentPoints(2, 1, example);

        assertEquals(3, points.size());
        assertTrue(points.contains(new Point(1, 1)));
        assertTrue(points.contains(new Point(2, 0)));
        assertTrue(points.contains(new Point(2, 2)));
    }

    @Test
    public void shouldGetAdjacentValuesFromLowerRight() {
        Set<Point> points = helper.getAdjacentPoints(2, 2, example);

        assertEquals(2, points.size());
        assertTrue(points.contains(new Point(1, 2)));
        assertTrue(points.contains(new Point(2, 1)));
    }

    @Test
    public void shouldCalculateAverageFromTwoValues() {
        Set<Point> points = Stream.of(
                new Point(0, 0),
                new Point(0, 1)
        ).collect(Collectors.toSet());
        String[][] dataset = new String[][]{{"59.865848", "70.807258"}};

        BigDecimal result = helper.getAverage(points, dataset);

        assertEquals(0, result.compareTo(new BigDecimal("65.336553")));
    }

    @Test
    public void shouldCalculateAverageFromThreeValues() {
        Set<Point> points = Stream.of(
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2)
        ).collect(Collectors.toSet());
        String[][] dataset = new String[][]{{"2.058449", "30.424224", "61.185289"}};

        BigDecimal result = helper.getAverage(points, dataset);

        assertEquals(0, result.compareTo(new BigDecimal("31.222654")));
    }

    @Test
    public void shouldCalculateAverageFromFourValues() {
        Set<Point> points = Stream.of(
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3)
        ).collect(Collectors.toSet());
        String[][] dataset = new String[][]{{"86.617615", "96.990985", "21.233911", "52.475643"}};

        BigDecimal result = helper.getAverage(points, dataset);

        assertEquals(0, result.compareTo(new BigDecimal("64.3295385")));
    }
}
