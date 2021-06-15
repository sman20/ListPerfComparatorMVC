package sv.spring.mvc.listperfcomp.models;

import sv.spring.mvc.listperfcomp.dao.TestCfgDAO;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class TestResults {
    private TestCfgDAO testCfgDAO;
    private List<Integer> list1;
    private List<Integer> list2;
    private String operationToTest;
    private int nmbOfCycles;
    private long list1Thread1Time;
    private long list1Thread2Time;
    private long list2Thread1Time;
    private long list2Thread2Time;

    public TestResults(TestCfgDAO testCfgDAO) {
        this.testCfgDAO = testCfgDAO;
        this.list1 = testCfgDAO.getTestCfg().getList1();
        this.list2 = testCfgDAO.getTestCfg().getList2();
        this.operationToTest = testCfgDAO.getTestCfg().getOperation();
        this.nmbOfCycles = testCfgDAO.getTestCfg().getTests();
    }

    public void testLists() {
//        System.out.println("List 1");
        long[] list1Results = calcListPerformanceAndReturnAvgTimeInMs(list1);
//        System.out.println("List 2");
        long[] list2Results = calcListPerformanceAndReturnAvgTimeInMs(list2);
        this.list1Thread1Time = list1Results[0];
        this.list1Thread2Time = list1Results[1];
        this.list2Thread1Time = list2Results[0];
        this.list2Thread2Time = list2Results[1];
    }

    private long[] calcListPerformanceAndReturnAvgTimeInMs(List<Integer> list) {
        long[] avgResults = new long[2];
        for (int i = 0; i < this.nmbOfCycles; i++) {
            long[] results = getListPerformance(list);
            avgResults[0] += results[0];
            avgResults[1] += results[1];
            testCfgDAO.resetAList(list);
        }
        avgResults[0] /= (this.nmbOfCycles * 1000);
        avgResults[1] /= (this.nmbOfCycles * 1000);
        return avgResults;
    }

    private long[] getListPerformance(List<Integer> list) {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Long> executorThread1 = executor.submit(new ListRunner(latch, list, this.operationToTest, 0, list.size() / 2));
        Future<Long> executorThread2 = executor.submit(new ListRunner( latch, list, this.operationToTest, list.size() / 2, list.size() ));
        latch.countDown();
        executor.shutdown();
        long[] threadResults = new long[2];
        try {
            threadResults = new long[]{executorThread1.get(), executorThread2.get()};
//            System.out.println(Arrays.toString(threadResults));
        } catch (InterruptedException e) {
            System.out.println("ERROR - Failed to complete threads, interrupted: " + e);
        } catch (ExecutionException e) {
            System.out.println("ERROR - Failed to execute threads: " + e);
        }
        return threadResults;
    }

    @Override
    public String toString() {
        return "Test Results (" +
                "operation: '" + operationToTest + '\'' +
                ", " + nmbOfCycles + " test cycles)" +
                "\nlist 1 type: " + list1.getClass().getSimpleName() +
                ", thread 1: " + list1Thread1Time +
                ", thread 2: " + list1Thread2Time +
                "\nlist 2 type: " + list2.getClass().getSimpleName() +
                ", thread 1: " + list2Thread1Time +
                ", thread 2: " + list2Thread2Time +
                '}';
    }

    public String getList1Name() {
        return list1.getClass().getSimpleName();
    }

    public String getList2Name() {
        return list2.getClass().getSimpleName();
    }

    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(List<Integer> list1) {
        this.list1 = list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void setList2(List<Integer> list2) {
        this.list2 = list2;
    }

    public String getOperationToTest() {
        return operationToTest;
    }

    public void setOperationToTest(String operationToTest) {
        this.operationToTest = operationToTest;
    }

    public int getNmbOfCycles() {
        return nmbOfCycles;
    }

    public void setNmbOfCycles(int nmbOfCycles) {
        this.nmbOfCycles = nmbOfCycles;
    }

    public long getList1Thread1Time() {
        return list1Thread1Time;
    }

    public void setList1Thread1Time(long list1Thread1Time) {
        this.list1Thread1Time = list1Thread1Time;
    }

    public long getList1Thread2Time() {
        return list1Thread2Time;
    }

    public void setList1Thread2Time(long list1Thread2Time) {
        this.list1Thread2Time = list1Thread2Time;
    }

    public long getList2Thread1Time() {
        return list2Thread1Time;
    }

    public void setList2Thread1Time(long list2Thread1Time) {
        this.list2Thread1Time = list2Thread1Time;
    }

    public long getList2Thread2Time() {
        return list2Thread2Time;
    }

    public void setList2Thread2Time(long list2Thread2Time) {
        this.list2Thread2Time = list2Thread2Time;
    }
}
