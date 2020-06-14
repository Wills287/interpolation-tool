package wills.interpolation.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wills.interpolation.service.DataProcessingService;

@Component
public class DataProcessingManager {

    private final DataProcessingService service;

    @Autowired
    public DataProcessingManager(DataProcessingService service) {
        this.service = service;
    }

    public String process(String body) {
        String[][] dataset = service.convertInput(body);
        String[][] output = service.processDataset(dataset);
        return service.convertOutput(output);
    }
}
