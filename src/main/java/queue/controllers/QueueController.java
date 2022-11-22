package queue.controllers;

import model.BasicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import queue.threads.ProcessedDataThread;
import requests.BasicDataRequest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
public class QueueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueController.class);
    private final Set<Integer> processedIds = Collections.synchronizedSet(new HashSet<>());
    private final Queue<Integer> queueToProcess = new LinkedBlockingQueue<>();
    @Value("${queue.config.max-pool}")
    private int maxPool;
    @Value("${queue.config.processingTime}")
    private int processingTime;
    private int usedPools = 0;
    private int currentQueueSize = 0;

    @GetMapping("/data/")
    public BasicEntry getBasicEntry(@RequestBody BasicDataRequest basicDataRequest) throws IOException {
        LOGGER.info("Gettind data for locator: {}", basicDataRequest.getLocator());
        int id = (int) (Math.random() * 10000);
        while (processedIds.contains(id)) {
            id = (int) (Math.random() * 10000);
        }
        queueToProcess.add(id);
        processedIds.add(id);
        BufferedWriter output = new BufferedWriter(new FileWriter("output.txt", true));
        output.newLine();
        output.write(usedPools + ", " + queueToProcess.size());
        output.close();
        while(usedPools >= maxPool) {

        }
        ProcessedDataThread processedDataThread = new ProcessedDataThread(processedIds, queueToProcess, processingTime);
        processedDataThread.start();
        usedPools += 1;
        currentQueueSize -= 1;
        while (processedIds.contains(id)) {

        }
        usedPools -= 1;
        return new BasicEntry(basicDataRequest.getLocator(), "TEMP");
    }
}
