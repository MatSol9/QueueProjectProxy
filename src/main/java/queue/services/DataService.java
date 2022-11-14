package queue.services;

import model.BasicEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataService.class);

    public DataService() {
    }

    public BasicEntry getData(String locator) {
        LOGGER.info("Gettind data for locator: {}", locator);
        return new BasicEntry(locator, "TEMP");
    }
}
