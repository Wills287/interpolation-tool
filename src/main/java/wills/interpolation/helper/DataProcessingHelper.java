package wills.interpolation.helper;

import org.springframework.stereotype.Component;
import wills.interpolation.model.Point;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataProcessingHelper {

    public Set<Point> getAdjacentPoints(int x, int y, String[][] dataset) {
        return getAdjacentPoints(x, y, dataset.length - 1, dataset[x].length - 1);
    }

    public Set<Point> getAdjacentPoints(int x, int y, int maximumX, int maximumY) {
        Set<Point> points = new HashSet<>();
        if (x > 0) {
            points.add(new Point(x - 1, y));
        }
        if (x < maximumX) {
            points.add(new Point(x + 1, y));
        }
        if (y > 0) {
            points.add(new Point(x, y - 1));
        }
        if (y < maximumY) {
            points.add(new Point(x, y + 1));
        }
        return points;
    }
}
