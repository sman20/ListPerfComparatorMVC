package sv.spring.mvc.listperfcomp.dao;

import org.springframework.stereotype.Component;
import sv.spring.mvc.listperfcomp.models.TestResults;

@Component
public class TestResultsDAO {

    TestResults testResults;

    public TestResults getTestResults(TestCfgDAO testCfgDAO) {
        testResults = new TestResults(testCfgDAO);
        testResults.testLists();
        return testResults;
    }

    public void printResults() {
        System.out.println(testResults);
    }
}
