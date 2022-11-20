package queue.threads;

import java.util.Set;

public class ProcessedDataThread extends Thread{
    private Set<Integer> processedData;
    private final int id;

    public ProcessedDataThread(Set<Integer> processedData, int id) {
        this.processedData = processedData;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processedData.remove(id);
    }
}
