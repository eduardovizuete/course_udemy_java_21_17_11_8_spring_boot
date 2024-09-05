package concurrency.codecourse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollection {
    public static void main(String[] args) {
        List<String> dogTypes = new ArrayList<>();
        dogTypes.add("German Shepherd");
        dogTypes.add("Labrador");
        List<String> dogTypesSyn = Collections.synchronizedList(dogTypes);

        // safe to use dogTypesSyn with multiple threads...
    }

}
