package sv.spring.mvc.listperfcomp.models;

import java.util.List;

public class TestCfg {
    private List<Integer> list1;
    private List<Integer> list2;
    private String name1;
    private String name2;
    private int size;
    private int min;
    private int max;
    private String operation;
    private int tests;

    public TestCfg() {         // default cfg
        String listName1 = "CopyOnWriteArrayList";
        this.list1 = ListUnderTestFactory.getListOrNull(listName1);
        String listName2 = "SynchronizedRandomAccessList";
        this.list2 = ListUnderTestFactory.getListOrNull(listName2);
        this.name1 = listName1;
        this.name2 = listName2;
        this.size = 0;
        this.min = 0;
        this.max = 0;
        this.operation = Operation.valueOf("READ").toString();
        this.tests = 0;
    }

    @Override
    public String toString() {
        String listName1 = list1 != null ? list1.getClass().getSimpleName() : "undefined";
        String listName2 = list2 != null ? list2.getClass().getSimpleName() : "undefined";
        return "TestCfg {" +
                listName1 + ", '" + name1 + '\'' + ", " +
                listName2 + ", '" + name2 + '\'' +
                ", size=" + size +
                ", min=" + min +
                ", max=" + max +
                ", '" + operation + '\'' +
                ", tests=" + tests +
                '}';
    }

    public void clearAndPopulateLists() {
        clearAndPopulateAList(this.list1);
        clearAndPopulateAList(this.list2);
    }

    public void clearAndPopulateAList(List<Integer> list) {
        list.clear();
        populate(list);
    }

    private void populate(List<Integer> list) {
        for (int i = 0; i < this.size; i++) {
            // random int from [min] included till [max] excluded
            int newMember = (int) (Math.random() * (this.max - this.min) + this.min);
            list.add(i, newMember);
        }
    }

    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(List<Integer> list1) {     // Java is passing by value, not reference! so passing a list, not just name
        this.list1 = list1;
        this.name1 = list1.getClass().getSimpleName();
//        this.list1 = ListUnderTestFactory.getListOrNull(list1name);
//        this.name1 = this.list1 == null ? "" : this.list1.getClass().getSimpleName();     // ????? when used, the lists do not change!
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void setList2(List<Integer> list2) {     // Java is passing by value not reference! so passing a list, not just name
        this.list2 = list2;
        this.name2 = list2.getClass().getSimpleName();
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public void setOperation(String operation) {
        this.operation = Operation.valueOf(operation).toString();   // IllegalArgumentException
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTests() {
        return tests;
    }

    public void setTests(int tests) {
        this.tests = tests;
    }

    public String getOperation() {
        return operation;
    }
}
