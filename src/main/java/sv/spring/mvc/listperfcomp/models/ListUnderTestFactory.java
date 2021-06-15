package sv.spring.mvc.listperfcomp.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListUnderTestFactory {

    public static List<Integer> getListOrNull(String listName) {
        switch (listName) {
            case "CopyOnWriteArrayList":
                return new CopyOnWriteArrayList<>();
            case "SynchronizedRandomAccessList":
                return Collections.synchronizedList(new ArrayList<Integer>());
            case "ArrayList":
                return new ArrayList<>();
            case "LinkedList":
                return new LinkedList<>();
            default:
                return null;
        }
    }
}
