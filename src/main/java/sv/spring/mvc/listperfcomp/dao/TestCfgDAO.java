package sv.spring.mvc.listperfcomp.dao;

import org.springframework.stereotype.Component;
import sv.spring.mvc.listperfcomp.models.ListUnderTestFactory;
import sv.spring.mvc.listperfcomp.models.TestCfg;

import java.util.Arrays;
import java.util.List;

@Component
public class TestCfgDAO {

    private TestCfg testCfg;

    {
        testCfg = new TestCfg();
    }

    public TestCfg getTestCfg() {
        return testCfg;
    }

    public void setTestCfg(TestCfg newTestCfg) {

        testCfg.setList1(ListUnderTestFactory.getListOrNull(newTestCfg.getName1()));
        testCfg.setList2(ListUnderTestFactory.getListOrNull(newTestCfg.getName2()));

        testCfg.setMin(newTestCfg.getMin());
        testCfg.setMax(newTestCfg.getMax());
        testCfg.setSize(newTestCfg.getSize());
        testCfg.setOperation(newTestCfg.getOperation());
        testCfg.setTests(newTestCfg.getTests());
    }

    public void resetLists() {
        testCfg.clearAndPopulateLists();
    }

    public void resetAList(List<Integer> list) {
        testCfg.clearAndPopulateAList(list);
    }

    public void printCfg() {
        System.out.println(testCfg);
    }

    public void printLists() {
        System.out.println("List 1 [" + testCfg.getName1() + "] : " + Arrays.toString(testCfg.getList1().toArray()));
        System.out.println("List 2 [" + testCfg.getName2() + "] : " + Arrays.toString(testCfg.getList2().toArray()));
    }
}
