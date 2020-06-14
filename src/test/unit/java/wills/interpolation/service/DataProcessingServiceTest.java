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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataProcessingServiceTest {

    @Mock
    private DataProcessingHelper helper;

    @InjectMocks
    private DataProcessingService service;

    @Test
    public void shouldConvertInputUsingCommas() {
        String input = "1,2,3";

        String[][] output = service.convertInput(input);

        assertEquals("1", output[0][0]);
        assertEquals("2", output[0][1]);
        assertEquals("3", output[0][2]);
    }

    @Test
    public void shouldConvertInputUsingLineBreaks() {
        String input = "1\n2\n3";

        String[][] output = service.convertInput(input);

        assertEquals("1", output[0][0]);
        assertEquals("2", output[1][0]);
        assertEquals("3", output[2][0]);
    }

    @Test
    public void shouldConvertInputUsingAll() {
        String input = "1,2,3\n4,5,6";

        String[][] output = service.convertInput(input);

        assertEquals("1", output[0][0]);
        assertEquals("2", output[0][1]);
        assertEquals("3", output[0][2]);
        assertEquals("4", output[1][0]);
        assertEquals("5", output[1][1]);
        assertEquals("6", output[1][2]);
    }

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

    @Test
    public void shouldConvertOutputUsingCommas() {
        String[][] output = new String[][]{{"1", "2"}};

        String result = service.convertOutput(output);

        assertEquals("1,2\n", result);
    }

    @Test
    public void shouldConvertOutputUsingLineBreaks() {
        String[][] output = new String[][]{{"1"}, {"2"}};

        String result = service.convertOutput(output);

        assertEquals("1\n2\n", result);
    }

    @Test
    public void shouldConvertOutputUsingAll() {
        String[][] output = new String[][]{{"1", "2"}, {"3", "4"}};

        String result = service.convertOutput(output);

        assertEquals("1,2\n3,4\n", result);
    }
}
