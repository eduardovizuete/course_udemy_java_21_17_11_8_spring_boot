package concurrency.codecourse;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteCollections {
    public static void main(String[] args) {
        List<String> names = new CopyOnWriteArrayList<>();
        names.add("Ann");
        names.add("Brian");
        names.add("Carol");

        // API: "The snapshot style iterator method uses a reference 
        //      to the state of the array at the point that the iterator was created. 
        //      This array never changes during the lifetime of the iterator, so 
        //      interference is impossible and the iterator is guaranteed not to throw 
        //      ConcurrentModificationException.". 
        for (String name : names) {
            System.out.println(name);
            names.add(name);
        }
        System.out.println(names);
        System.out.println("--------------------------------------");

        Set<String> uniqueNames = new CopyOnWriteArraySet<>();
        uniqueNames.add("Ann");
        uniqueNames.add("Brian");
        uniqueNames.add("Carol");
        for (String name : uniqueNames) {
            System.out.println(name);
            uniqueNames.add(name);
        }
        System.out.println(uniqueNames);
        System.out.println("Size is " + uniqueNames.size());
    }

}
