package lambdas.assignment;

import lambdas.assignment.interfaces.Evaluate;
import lambdas.assignment.interfaces.Functionable;
import lambdas.assignment.interfaces.Printable;
import lambdas.assignment.interfaces.Retrievable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {
    public static void main(String[] args) {
        consumer();
        suplier();
        predicate();
        function();

        List<Person> listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    /*
        1. In main() invoke the consumer() method; in consumer() do the following:
            a) Using a lambda expression, implement the Printable interface (typed for String).
            The relevant method just prints out the String argument it receives.
            Invoke the relevant method, passing in "Printable lambda".

            b) Using both a lambda expression and a method reference, implement 1a using a Consumer.
    */
    private static void consumer() {
        Printable<String> printable = s -> System.out.println(s); // lambda
        printable.print("Printable lambda");

        Consumer<String> printConsumer = s -> System.out.println(s); // lambda
        printConsumer.accept("Consumer lamda");

        Consumer<String> printConsumerMR = System.out::println; // method reference
        printConsumer.accept("Consumer method reference");
    }

    /*
        2. In main() invoke the supplier() method; in supplier() do the following:
            a) Using a lambda expression, implement the Retrievable interface (typed for Integer).
            The relevant method just returns 77. Invoke the relevant method.

            b) Using a lambda expression, implement 2a using a Supplier.
    */
    private static void suplier() {
        Retrievable<Integer> retrievable = () -> 77;
        System.out.println("Retrievable " + retrievable.retrieve());

        Supplier<Integer> supplier = () -> 77;
        System.out.println("Suplier " + supplier.get());
    }

    /*
        3. In main() invoke the predicate() method; in predicate() do the following:
            a) Using a lambda expression, implement the Evaluate interface (typed for Integer).
            The relevant method returns true if the argument passed is < 0, otherwise it returns false.
            Invoke the relevant method twice – the first time pass in -1 and the second time pass in +1

            b) Using a lambda expression, implement 3a using a Predicate.

            c) Declare a generically-typed check() method (not in UML).
            The first parameter is generic and the second parameter is a Predicate, also generically typed.
            The check() method returns true/false.
            Invoke the check() method with the following Predicate lambda expressions:
            we want to know if a number is even (true) – invoke check() with 4 and 7 (true and false).
            we want to know if a String begins with “Mr.” – invoke check() with “Mr. Joe Bloggs” and
            “Ms. Ann Bloggs”
            we want to know if a person is an adult (age >= 18) – invoke check() with “Mike” who is 33
            and 1.8 (metres assumed) in height; and “Ann” who is 13 and 1.4 (metres) in height.
    */
    private static void predicate() {
        Evaluate<Integer> evaluate = number -> number < 0;
        System.out.println("Evaluate " + evaluate.isNegative(-1));
        System.out.println("Evaluate " + evaluate.isNegative(1));

        Predicate<Integer> predicateEval = number -> number < 0;
        System.out.println("Predicate " + predicateEval.test(-1));
        System.out.println("Predicate " + predicateEval.test(1));

        Predicate<Integer> predicateEvenNumber = number -> (number % 2) == 0;
        System.out.println("Is 4 even? " + check(4, predicateEvenNumber));
        System.out.println("Is 7 even? " + check(7, predicateEvenNumber));

        Predicate<String> predicateStringBegin = string -> string.startsWith("Mr.");
        System.out.println("Does Mr. Joe Bloggs start with Mr. ? " + check("Mr. Joe Bloggs", predicateStringBegin));
        System.out.println("Does Ms. Ann Bloggs start with Mr. ? " + check("Ms. Ann Bloggs", predicateStringBegin));

        Predicate<Person> personPredicate = p -> p.getAge() >= 18;
        Person person = new Person("Mike", 33, 1.8);
        System.out.println("Is " + person.getName() + " an adult? " + check(person, personPredicate));
        person = new Person("Ann", 13, 1.4);
        System.out.println("Is " + person.getName() + " an adult? " + check(person, personPredicate));
    }

    public static <T> boolean check(T t, Predicate<T> lambda) {
        return lambda.test(t);
    }

    /*
        4. In main() invoke the function() method; in function() do the following:
            a) Using a lambda expression, implement the Functionable interface
            - the input type is Integer and the return type is String.
            The relevant method returns the number passed in appended to the String
            “Number is: ”. Invoke the relevant method passing in 25.

            b) Using a lambda expression, implement 4a using a Function.
    */
    public static void function() {
        Functionable<Integer, String> functionable = number -> String.format("Number is: %s", number);
        System.out.println("Functionable: " + functionable.applyThis(25));

        Function<Integer, String> functionConcat = number -> String.format("Number is: %s", number);
        System.out.println("Function: " + functionConcat.apply(25));
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

    /*
        6. In main(), invoke the sortAge() method passing down listPeople; in sortAge() do the following:
            a) Using the Iterable sort() method (note: List extends Iterable), and the Comparator.comparing()
            method, sort the Person objects in ascending age order. Note that the argument to
            Comparator.comparing() requires a Function (In, Out) that returns a Comparable (a class that
            implements Comparable). From that, the comparing() method generates a Comparator that it passes
            to the sort() method.
                Note that as of Java 8, the List interface supports the sort() method directly so there is no
                need to use the Collections.sort(): i.e. instead of Collections.sort(list, comparatorRef); we
                now have list.sort(comparatorRef);

            b) Output the sorted list using the Iterable forEach() method passing in a lambda expression.
     */
    private static void sortAge(List<Person> listPeople) {
        /* option 1:
        Comparator<Person> comparatorByAge = Comparator.comparing(
                Person::getAge
        );

        listPeople.sort(comparatorByAge);
         */

        // option 2:
        listPeople.sort(
                Comparator.comparing(p -> p.getAge()) // lambda syntax
                //Comparator.comparing(Person::getAge) // method reference syntax
        );

        System.out.println("After sort by edge");
        listPeople.forEach(person -> System.out.println(person)); // lambda
        //listPeople.forEach(System.out::println)); // method reference
    }

    /*
        7. In main(), invoke the sortName() method passing down listPeople; in sortName() do the following:
            a) As in 6a except sort the Person objects in ascending name order.
            b) Output the sorted list using the Iterable forEach() method passing in a lambda expression.
     */
    private static void sortName(List<Person> listPeople) {
        /* option 1:
        Comparator<Person> comparatorByName = Comparator.comparing(
                Person::getName
        );

        listPeople.sort(comparatorByName);
         */

        // option 2:
        listPeople.sort(
                Comparator.comparing(p -> p.getName()) // lambda syntax
                //Comparator.comparing(Person::getName) // method reference syntax
        );

        System.out.println("After sort by name");
        listPeople.forEach(person -> System.out.println(person)); // lambda
        //listPeople.forEach(System.out::println)); // method reference

    }

    /*
        8. In main(), invoke the sortHeight() method passing down listPeople; in sortHeight() do the following:
            a) As in 6a except sort the Person objects in ascending height order.
            b) Output the sorted list using the Iterable forEach() method passing in a lambda expression.
     */
    private static void sortHeight(List<Person> listPeople) {
        /* option 1:
        Comparator<Person> comparatorByName = Comparator.comparing(
                Person::getHeight
        );

        listPeople.sort(comparatorByName);
         */

        // option 2:
        listPeople.sort(
                Comparator.comparing(p -> p.getHeight()) // lambda syntax
                //Comparator.comparing(Person::getHeight) // method reference syntax
        );

        System.out.println("After sort by height");
        listPeople.forEach(person -> System.out.println(person)); // lambda
        //listPeople.forEach(System.out::println)); // method reference
    }

}
