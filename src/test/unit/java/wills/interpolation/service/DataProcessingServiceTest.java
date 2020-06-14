package wills.interpolation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wills.interpolation.helper.DataProcessingHelper;
import wills.interpolation.model.Point;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataProcessingServiceTest {

    @Mock
    private DataProcessingHelper helper;

    @InjectMocks
    private DataProcessingService service;

    @Test
    public void shouldReturnInputDatasetGivenNoMissingValues() {
        String[][] expected = new String[][]{{"1", "2"}, {"3", "4"}};

        assertArrayEquals(expected, service.processDataset(expected));
    }

    @Test
    public void shouldReturnInterpolatedDatasetGivenMissingValue() {
        String[][] input = new String[][]{{"nan", "2"}, {"3", "4"}};
        String[][] expected = new String[][]{{"2.5", "2"}, {"3", "4"}};
        Set<Point> points = new HashSet<>();

        when(helper.getAdjacentPoints(0, 0, input)).thenReturn(points);
        when(helper.getAverage(points, input)).thenReturn(new BigDecimal("2.5"));

        assertArrayEquals(expected, service.processDataset(input));
    }
}
