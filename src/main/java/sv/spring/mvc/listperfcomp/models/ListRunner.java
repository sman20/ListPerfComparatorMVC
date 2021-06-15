package sv.spring.mvc.listperfcomp.models;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ListRunner implements Callable<Long> {
    private CountDownLatch latch;
    private List<Integer> list;
    private String operation;
    private int start;
    private int end;

    public ListRunner(CountDownLatch latch, List<Integer> list, String operation, int start, int end) {
        this.latch = latch;
        this.list = list;
        this.operation = operation;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long call() throws InterruptedException {
        latch.await();
        long startTimeInNs = System.nanoTime();
        for (int i = start; i < end; i++) {
            switch (Operation.valueOf(operation.toUpperCase())) {
                case ADD:
                    list.add(i);
                case READ:
                    list.get(i);
            }
        }
        return System.nanoTime() - startTimeInNs;
    }
}
