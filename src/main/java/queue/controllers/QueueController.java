package queue.controllers;

import model.BasicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import queue.services.DataService;
import requests.BasicDataRequest;

@RestController
public class QueueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueController.class);
    private final DataService dataService;

    public QueueController(DataService dataService) {
        LOGGER.info("Setting up QueueController");
        this.dataService = dataService;
    }

    @GetMapping("/data/")
    public BasicEntry getBasicEntry(@RequestBody BasicDataRequest basicDataRequest) {
        return dataService.getData(basicDataRequest.getLocator());
    }
}
