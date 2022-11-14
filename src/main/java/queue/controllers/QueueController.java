package queue.controllers;

import model.BasicEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import queue.services.DataService;
import requests.BasicDataRequest;

@RestController
public class QueueController {
    private final DataService dataService;

    public QueueController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data/")
    public BasicEntry getBasicEntry(@RequestBody BasicDataRequest basicDataRequest) {
        return dataService.getData(basicDataRequest.getLocator());
    }
}
