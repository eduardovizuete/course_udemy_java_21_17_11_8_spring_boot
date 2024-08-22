package lambdas_methodref.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaAndMethodReferences {

    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    /**
        1. Static method references:
             a. in staticMR(), declare a List of integers with 1, 2, 7, 4, and 5 as values.
             b. using a Consumer typed for List<Integer> and the Collections.sort static
                method, code a lambda that sorts the list passed in.
             c. invoke the lambda.
             d. prove that the sort worked.
             e. re-initialise the list (so it is unsorted again).
             f. code the method reference version.
                i. sort() is overloaded : sort(List) and sort(List, Comparator)
                ii. how does Java know which version to call?
             g. invoke the method reference version.
             h. prove that the sort worked.
     */
    private static void staticMR() {
        List<Integer> listOfNumbers = Arrays.asList(1,2,7,4,5);

        Consumer<List<Integer>> sortListLambda = (list) -> Collections.sort(list);
        sortListLambda.accept(listOfNumbers);
        System.out.printf("staticMR() -> sortListLambda : %s %n", listOfNumbers);

        listOfNumbers = Stream.of(1,2,7,4,5).collect(Collectors.toList());

        Consumer<List<Integer>> sortListMetRef = Collections::sort;
        sortListMetRef.accept(listOfNumbers);
        System.out.printf("staticMR() -> sortListMetRef : %s %n", listOfNumbers);
    }

    /**
        Bound method references (calling instance methods on a particular object):
            a. in boundMR(), declare a String variable called name and initialise it to “Mr. Joe
                Bloggs”.
            b. using a Predicate typed for String, code a lambda that checks to see if name
                starts with the prefix passed in.
            c. invoke the lambda passing in “Mr.” which should return true.
            d. invoke the lambda passing in “Ms.” which should return false.
            e. code the method reference version.
            f. repeat c and d above except using the method reference version.
    */
    private static void boundMR() {
        String name = "Mr. Joe Bloggs";

        Predicate<String> predStartWithLambda = (prefix) -> name.startsWith(prefix);
        System.out.printf("boundMR() -> predStartWithLambda : %s starts with %s %s %n", name, "Mr.", predStartWithLambda.test("Mr."));
        System.out.printf("boundMR() -> predStartWithLambda : %s starts with %s %s %n", name, "Ms.", predStartWithLambda.test("Ms."));

        Predicate<String> predStartWithMetRef = name::startsWith;
        System.out.printf("boundMR() -> predStartWithMetRef : %s starts with %s %s %n", name, "Mr.", predStartWithMetRef.test("Mr."));
        System.out.printf("boundMR() -> predStartWithMetRef : %s starts with %s %s %n", name, "Ms.", predStartWithMetRef.test("Ms."));
    }

    /**
        Unbound method references (calling instance methods on a parameter):
            a. in unboundMR(), code a Predicate lambda typed for String that checks to see if
            the string passed in is empty.
            b. invoke the lambda passing in “” (returns true).
            c. invoke the lambda passing in “xyz” (returns false).
            d. code the method reference version of the lambda from (a).
            e. repeat b and c above except using the method reference version.
            f. code a BiPredicate lambda typed for String and String:
                i. the lambda takes in two parameters (hence “Bi”)
                ii. check if the first parameter starts with the second parameter
            iii. invoke the lambda twice:
                    1. passing in “Mr. Joe Bloggs” and “Mr.” (returns true)
                    2. passing in “Mr. Joe Bloggs” and “Ms.” (returns false)
            g. code the method reference version of the lambda from (f).
            h. test it as per above in (f.iii)
    */
    private static void unboundMR() {
        Predicate<String> predIsEmptyLambda = (str) -> str.isEmpty();
        System.out.printf("unboundMR() -> predIsEmptyLambda : %s is empty %s%n", "", predIsEmptyLambda.test(""));
        System.out.printf("unboundMR() -> predIsEmptyLambda : %s is empty %s%n", "xyz", predIsEmptyLambda.test("xyz"));

        Predicate<String> predIsEmptyMetref = String::isEmpty;
        System.out.printf("unboundMR() -> predIsEmptyMetref : %s is empty %s%n", "", predIsEmptyMetref.test(""));
        System.out.printf("unboundMR() -> predIsEmptyMetref : %s is empty %s%n", "xyz", predIsEmptyMetref.test("xyz"));

        BiPredicate<String, String> biPredIsEmptyLambda = (str, prefix) -> str.startsWith(prefix);
        System.out.printf("unboundMR() -> biPredIsEmptyLambda : %s starts with %s %s %n", "Mr. Joe Bloggs", "Mr.", biPredIsEmptyLambda.test("Mr. Joe Bloggs", "Mr."));
        System.out.printf("unboundMR() -> biPredIsEmptyLambda : %s starts with %s %s %n", "Mr. Joe Bloggs", "Ms.", biPredIsEmptyLambda.test("Mr. Joe Bloggs", "Ms."));

        BiPredicate<String, String> biPredIsEmptyMetRef = String::startsWith;
        System.out.printf("unboundMR() -> biPredIsEmptyMetRef : %s starts with %s %s %n", "Mr. Joe Bloggs", "Mr.", biPredIsEmptyMetRef.test("Mr. Joe Bloggs", "Mr."));
        System.out.printf("unboundMR() -> biPredIsEmptyMetRef : %s starts with %s %s %n", "Mr. Joe Bloggs", "Ms.", biPredIsEmptyMetRef.test("Mr. Joe Bloggs", "Ms."));
    }

    /**
        4. Constructor method references:
            a. in constructorMR(), code a Supplier typed for List<String> that returns a new
            ArrayList.
            b. invoke the lambda to create a new List<String> named list.
                    c. add “Lambda” to the list.
            d. output the list to show it worked.
            e. code the method reference version of the lambda:
                i. re-initialise list by invoking the method reference version.
                ii. add “Method Reference” to the list.
                iii. output the list to show it worked.
            f. next, we want to use the overloaded ArrayList constructor passing in 10 as the
                initial capacity (note: the default constructor assumes a capacity of 10).
                i. thus, we need to pass IN something and get back OUT something:
                    1. IN: 10
                        OUT: ArrayList
                ii. we need a Function typed for Integer and List<String> for this.
                iii. code the lambda.
                iv. re-initialise the list by invoking the lambda passing in 10 as the capacity.
                v. add “Lambda” to the list.
                vi. output the list to show it worked.
            g. code the method reference version.
                i. note that the method reference version is the exact same as above in e!!
                ii. this is where context is all important:
                    1. the first method reference was for a Supplier and Supplier’s
                        functional method is T get() and thus, Java knew to look for the
                        ArrayList constructor that takes in NO argument
                    2. the first method reference was for a Function and Function’s
                        functional method is R apply(T t) and thus, Java knew to look for
                        the ArrayList constructor that takes in ONE argument.
     */
    private static void constructorMR() {
        Supplier<List<String>> supplierListLamba = () -> new ArrayList<>();
        List list = supplierListLamba.get();
        list.add("Lambda");
        System.out.printf("constructorMR() -> supplierListLamba %s %n", list);

        Supplier<List<String>> supplierListMetRef = ArrayList::new;
        list = supplierListMetRef.get();
        list.add("Method Reference");
        System.out.printf("constructorMR() -> supplierListMetRef %s %n", list);

        Function<Integer, List<String>> functionListLambda = (capacity) -> new ArrayList<>(capacity);
        list = functionListLambda.apply(10);
        list.add("Lambda");
        System.out.printf("constructorMR() -> functionListLambda %s %n", list);

        Function<Integer, List<String>> functionListMetRef = ArrayList::new; // context!
        list = functionListMetRef.apply(10);
        list.add("Method Reference");
        System.out.printf("constructorMR() -> functionListMetRef %s %n", list);
    }

}
