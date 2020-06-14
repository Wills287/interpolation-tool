package wills.interpolation.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wills.interpolation.service.DataProcessingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataProcessingManagerTest {

    @Mock
    private DataProcessingService service;

    @InjectMocks
    private DataProcessingManager manager;

    @Test
    public void shouldCallServiceToConvertInput() {
        String body = "body";

        manager.process(body);

        verify(service).convertInput(body);
    }

    @Test
    public void shouldCallServiceToProcessDataset() {
        String body = "body";
        String[][] dataset = new String[0][0];

        when(service.convertInput(body)).thenReturn(dataset);

        manager.process(body);

        verify(service).processDataset(dataset);
    }

    @Test
    public void shouldCallServiceToConvertOutput() {
        String body = "body";
        String[][] dataset = new String[0][0];
        String[][] output = new String[0][0];

        when(service.convertInput(body)).thenReturn(dataset);
        when(service.processDataset(dataset)).thenReturn(output);

        manager.process(body);

        verify(service).convertOutput(output);
    }

    @Test
    public void shouldReturnConvertedOutput() {
        String body = "body";
        String[][] dataset = new String[0][0];
        String[][] output = new String[0][0];
        String expected = "result";

        when(service.convertInput(body)).thenReturn(dataset);
        when(service.processDataset(dataset)).thenReturn(output);
        when(service.convertOutput(output)).thenReturn(expected);

        assertEquals(expected, manager.process(body));
    }
}
