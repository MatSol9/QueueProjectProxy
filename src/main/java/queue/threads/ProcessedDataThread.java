package queue.threads;

import java.util.Queue;
import java.util.Set;

public class ProcessedDataThread extends Thread{
    private final Set<Integer> processedData;
    private final Queue<Integer> queue;
    private final int processingTime;

    public ProcessedDataThread(Set<Integer> processedData, Queue<Integer> ids, int processingTime) {
        this.processedData = processedData;
        this.queue = ids;
        this.processingTime = processingTime;
    }

    @Override
    public void run() {
        int id = queue.remove();
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processedData.remove(id);
    }
}
