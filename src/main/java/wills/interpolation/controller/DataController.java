package wills.interpolation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wills.interpolation.manager.DataProcessingManager;

@Controller
public class DataController {

    private final DataProcessingManager manager;

    @Autowired
    public DataController(DataProcessingManager manager) {
        this.manager = manager;
    }

    @PostMapping(
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> create(@RequestBody String body) {
        return ResponseEntity.ok(manager.process(body));
    }
}
