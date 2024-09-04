package streams.assignment;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamQuestionsIds {

    public static void main(String[] args) {
        qid_q2_2023();
        qid_q2_1762();
        qid_q2_1787();
        qid_q2_1738();
        qid_q2_1826();
        qid_q2_1809();
        qid_q2_1846();
        qid_q2_1847();
        qid_q2_1810();
        qid_q2_1849();
        qid_q2_1858();
        qid_q2_2024();
        qid_q2_1840();
        qid_q2_1841();
    }

    /*
        1. Stream a list of int primitives between the range of 0 (inclusive) and 5 (exclusive). Calculate and
        output the average.

        (QID 2.2023)
     */
    private static void qid_q2_2023() {
        IntStream intStream = IntStream.range(0, 5);
        OptionalDouble average = intStream.average();
        System.out.printf("IntStream range average = %s %n", average.orElse(0));
    }

    /*
        2. Given the Item class (in the zip file), declare a List typed for Item with the following Item’s:
                a. id=1 name=”Screw”
                b. id=2 name=”Nail”
                c. id=3 name=”Bolt”
            Stream the list and sort it so that it outputs “BoltNailScrew” i.e. alphabetic name order.
            Use Stream’s forEach method to output the names (use the method reference version for the
            required Consumer lambda).

        (QID Q2.1762)
     */
    private static void qid_q2_1762() {
        List<Item> itemList = Stream.of(
                new Item(1, "Screw"),
                new Item(1, "Nail"),
                new Item(1, "Bolt")
        ).toList();

        itemList.stream()
                .sorted(Comparator.comparing(Item::getName))
                .forEach(System.out::print);

        //itemList.sort(Comparator.comparing(Item::getName));
        //itemList.forEach(System.out::print);
    }

    /*
        3. Generate a Stream<List<String>> using the Stream.of(Arrays.asList(“a”, “b”), Arrays.asList(“a”,
        “c”)) method call. Filter the stream so that only list’s that contain “c” make it through the filter.
        Flatten the Stream<List<String>> to a Stream<String> using the flatMap() operation. Note that
        flapMap() states in the API “Each mapped stream is closed after its contents have been placed into
        this [new] stream.”. Use forEach() to output the new stream.

        (QID 2.1787)
     */
    private static void qid_q2_1787() {
        Stream<List<String>> streamOfLists = Stream.of(
                Arrays.asList("a", "b"),
                Arrays.asList("a", "c"));

        streamOfLists
                // Stream<List<String>> filter(Predicate)   Predicate == boolean test(T t)
                .filter(list -> list.contains("c"))
                // Stream<List<String>> peek(Consumer)
                .peek(list -> System.out.println("\n" + list)) // 2. [a,c]
                // Stream<String> flatMap(Function)
                //      Function<T,R> == R apply(T t)
                //          Stream<String> apply(List<String> l)
                .flatMap(list -> list.stream())
//                .flatMap(List::stream)
                //     forEach() is a terminal operation
                .forEach(str -> System.out.print(str + " ")); // 2.  a c
    }

    /*
        There are several parts to this:
        a. Using 1, 2 and 3 create a List of Integers.
            i. Stream the list and calculate the sum, using the sum() method from IntStream.
            ii. Stream the list again and calculate the maximum value, using the max() method from
            IntStream.
        b. Given the Person class (in the zip file), declare a List typed for Person with the following
        Person’s:
            i. “Alan”, “Burke”, 22
            ii. “Zoe”, “Peters”, 20
            iii. “Peter”, “Castle”, 29
            Using the max(Comparator) from Stream, calculate the oldest person in the list.
        c. Using 10, 47, 33 and 23 create a List of Integers. Stream the list and using the following
        versions of reduce(), calculate the maximum value:
            i. Optional<T> reduce(BinaryOperator<T> accumulator)
            ii. T reduce(T identity, BinaryOperator<T> accumulator)

        QID 2.1738
     */
    private static void qid_q2_1738() {
        // a1. sum() is not in Stream it is in IntStream
        List<Integer> listInt = Arrays.asList(1, 2, 3);
        Integer sum = listInt.stream()
                // IntStream mapToInt(ToIntFunction)
                // toIntFunction is a functional interface:
                //      int applyAsInt(T value)
                .mapToInt(i -> i)
                .sum();
        System.out.println();
        System.out.println("sum == " + sum);

        // a2. max() in IntStream
        int max = listInt.stream()
                // IntStream mapToInt(ToIntFunction)
                // toIntFunction is a functional interface:
                //      int applyAsInt(T value)
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        System.out.println("max == " + max);

        // b1
        List<Person> people = Arrays.asList(
                new Person("Alan", "Burke", 22),
                new Person("Zoe", "Peters", 20),
                new Person("Peter", "Castle", 29)
        );
        Person oldestPerson = people.stream()
                // Optional<Person> max(Comparator)
                // Comparator Comparator.comparing(Function that returns a Comparable)
                // comparing() takes the functional interface Function:
                //      R apply(T t)
                //      p.getAge() returns an Integer (is a Comparable)
                .max(Comparator.comparing(p -> p.getAge()))
                .get();
        System.out.println(oldestPerson);

        // c.
        List<Integer> integerList = Arrays.asList(10, 47, 33, 23);
        max = integerList.stream()
                // Optional<Integer> reduce(BinaryOperator<Integer> acc)
                // BinaryOperator<T> extends functional interface BiFunction<T,U,R>
                //    BiFunction's functional method is R apply(T t, U u)
                //      .reduce((a, b) -> a > b ? a : b)
                .reduce(
                        (a, b) -> Integer.max(a, b)
                ).get();
        System.out.println(max);

        max = integerList.stream()
                // Integer reduce(Integer identity, BinaryOperator<Integer> acc)
                // The identity element is both the initial value of the reduction
                // and the default result if there are no elements in the stream.
                .reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
        System.out.println(max);
    }

    /*
        5. Code a method public static Optional<String> getGrade(int marks)
            (QID 2.1826)
            a. in the method getGrade:
                i. declare an empty optional, typed for String called grade
                ii. insert the following code:
                    if (marks > 50) {grade = Optional.of(“PASS”);} else {grade.of(“FAIL”);}
            b. in main():
                i. declare an Optional, typed for String named grade1 which is initialised to the return
                    value of calling getGrade(50)
                ii. declare an Optional, typed for String named grade2 which is initialised to the return
                    value of calling getGrade(55)
                iii. using orElse() on grade1, output the value of grade1 or “UNKNOWN”
                iv. if(grade2.isPresent()) is true: use ifPresent(Consumer) to output the contents of
                    grade2; if false, use orElse() to output the contents of grade2 or “Empty”
            v. Notes:
            1. Optional’s are immutable.
            2. Optional.of(null); // NullPointerException
            3. Optional.ofNullable(null); // Optional.empty returned

        (QID 2.1826)
     */
    private static void qid_q2_1826() {
        Optional<String> grade1 = getGrade(50);// returns an empty Optional
        Optional<String> grade2 = getGrade(55);// returns "PASS" Optional

        System.out.println("Grade1 = " + grade1.orElse("UNKNOWN")); // UNKNOWN
        System.out.print("Grade2 = "); // UNKNOWN

        if(grade2.isPresent()){
            grade2.ifPresent(System.out::println);// PASS
        }else{
            System.out.println(grade2.orElse("Empty"));
        }
    }

    public static Optional<String> getGrade(int marks) {
        Optional<String> grade = Optional.empty();
        if (marks > 50) {
            grade = Optional.of("PASS");
        } else {
            grade.of("FAIL");// Optionals are immutable!
        }

        return grade;
    }

    /*
        6. Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
            a. title=”Thinking in Java”, price=30.0
            b. title=”Java in 24 hrs”, price=20.0
            c. title=”Java Recipes”, price=10.0
            Stream the books and calculate the average price of the books whose price is > 10.
            Change the filter to books whose price is > 90. Ensure you do not get an exception.

            (QID 2.1809)
     */
    private static void qid_q2_1809() {
        List<Book> bookList = Arrays.asList(
                new Book("Thinking in Java", 30.0),
                new Book("Java in 24 hrs", 20.0),
                new Book("Java Recipes", 0.0)
        );
        double averagePrice = bookList.stream()
                .filter(book -> book.getPrice() > 90)
                // DoubleStream mapToDouble(ToDoubleFunction)
                //   ToDoubleFunction is a functional interface:
                //      double applyAsDouble(T value)
                .mapToDouble(book -> book.getPrice())
                // OptionalDouble average()  - terminal operation
                .average()
                // .getAsDouble();
                .orElse(0.0); // useful if filter filters out ALL of the Books
        System.out.println("qid_q2_1809 average = " + averagePrice); // UNKNOWN
    }

    /*
        7. Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
                a. title=”Atlas Shrugged”, price=10.0
                b. title=”Freedom at Midnight”, price=5.0
                c. title=”Gone with the wind”, price=5.0
            Stream the books and instantiate a Map named ‘bookMap’ that maps the book title to its price. To do
            this use the collect(Collectors.toMap(Function fnToGetKey, Function fnToGetValue)). Iterate
            through ‘bookMap’ (using the Map forEach(BiConsumer) method). The BiConsumer only outputs
            prices where the title begins with “A”.

            (QID 2.1846)
     */
    private static void qid_q2_1846() {
        System.out.println("-------- qid_q2_1846");
        List<Book> bookList = Arrays.asList(
                new Book("Atlas Shrugged", 10.0),
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0)
        );

        // API:
        //   An object that maps keys to values.
        //   A map cannot contain duplicate keys; each key can map to at most one value.
        Map<String, Double> bookMap = bookList.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice));

        BiConsumer<String, Double> functionBC = (title, price) -> { //define the lambda (not executing yet)
            if (title.startsWith("A")) {
                System.out.println(price);
            }
        };
        System.out.println("using Map<String, Double> -> ");
        bookMap.forEach(functionBC); // execute lambda;   Map::forEach(BiConsumer)

        // option D
        // using a Set here as opposed to a Map
        Set<Map.Entry<String, Double>> bookSet = bookMap.entrySet();
        Consumer<Map.Entry<String, Double>> funcC = (e) -> {
            if(e.getKey().startsWith("A")){
                System.out.println(e.getValue());
            }
        };
        System.out.println("using Set<Map.Entry<String, Double>> -> ");
        bookSet.forEach(funcC); // Set::forEach(Consumer)
    }

    /*
        8. Given the Book class (in the zip file), declare a List typed for Book with the following Book’s:
                a. title=”Gone with the wind”, price=5.0
                b. title=”Gone with the wind”, price=10.0
                c. title=”Atlas shrugged”, price=15.0
            In a pipeline which has no return type:
            - stream the books
            - using the collect() method, generate a Map that maps the book title to its price
            - using forEach(), output the title and price of each entry in the map
            What happened and why? Fix this by using the Collectors.toMap(Function, Function,
            BinaryOperator) method.

            (QID 2.1847)
     */
    private static void qid_q2_1847() {
        System.out.println("-------- qid_q2_1847");
        List<Book> bookList = Arrays.asList(
                new Book("Gone with the wind", 5.0),
                new Book("Gone with the wind", 10.0),
                new Book("Atlas shrugged", 15.0)
        );

        bookList.stream()
                //.collect(Collectors.toMap(Book::getTitle, Book::getPrice)) //  IllegalStateException
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice, (v1, v2) -> v1 * v2))
                .forEach((a, b)->System.out.println(a+" "+b));
    }

    /*
        9. Given the Person class (in the zip file), declare a List typed for Person with the following Person’s:
                a. name=”Bob”, age=31
                b. name=”Paul”, age=32
                c. name=”John”, age=33
            Pipeline the following where the return type is double:
                - stream the people
                - filter the stream for Person’s whose age is < 30
                - map to int primitives
                - calculate the average age.
            This should generate a NoSuchElementException. Using orElse(), fix the pipeline (not the filter) so
            that 0.0 is returned instead of an exception being generated.

            (QID 2.1810)
     */
    private static void qid_q2_1810() {
        System.out.println("-------- qid_q2_1810");
        List<Person> personList = Arrays.asList(
                new Person("Bob", "Kelly", 31),
                new Person("Paul", "Landers", 32),
                new Person("John", "Paters", 33)
        );

        double averageAge = personList.stream()
                // Stream<Person> filter(Predicate)
                .filter(person -> person.getAge() < 30)
                // IntStream mapToInt(ToIntFunction)
                //    ToIntFunction
                //       int applyAsInt(T t)
                .mapToInt(Person::getAge)
                // OptionalDouble average()
                .average()
                // .getAsDouble(); // NoSuchElementException: No value present
                .orElse(0.0);

        System.out.println(averageAge);
    }

    /*
        10. A question about Optional. Let us look at this in parts:
            a. Declare an Optional, typed for Double, named ‘price’ using the Optional.ofNullable(20.0).
                Output the Optional value for ‘price’ 3 times: using ifPresent(Consumer), orElse(T) and
                orElseGet(Supplier).
            b. declare a new Optional, typed for Double, named ‘price2’ (or comment out (a) and re-use
                ‘price’). Use Optional.ofNullable again but this time, pass in null.
                    i. Output ‘price2’ in a normal System.out.println().
                    ii. check to see if price2 isEmpty() and if so output “empty”.
                    iii. do (ii) again except this time use the more functional “ifPresent(Consumer)” method.
                    iv. initialise a Double x to the return of “price2.orElse(44.0)”. Output and observe the
                        value of x.
            c. declare a new Optional, typed for Double, named ‘price3’ (or comment out (b) and re-use
                ‘price’). Use Optional.ofNullable passing in null.
                    i. initialise a Double z to the return of “price3.orElseThrow(() -> new
                    RuntimeException(“Bad Code”). Output and observe the value of z.

        (QID 2.1849)
     */
    private static void qid_q2_1849() {
        System.out.println("-------- qid_q2_1849");
        // ofNullable explained:
        //      Optional o = Optional.ofNullable(value)
        //          is the same as:
        //      Optional o = (value == null) ? Optional.empty() : Optional.of(value)
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(System.out::println); // 20.0
        Double value2 = price.orElse(0.0);
        System.out.println(value2); // 20.0
        Double value3 = price.orElseGet(() -> 0.0);
        System.out.println(value2); // 20.0

        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        if (price2.isEmpty()){
            System.out.println("empty"); // "empty"
        }
        price2.ifPresent(System.out::println);

        Double x = price2.orElse(44.0);
        System.out.println(x);

        Optional<Double> price3 = Optional.ofNullable(null);
        //Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code")); //java.lang.RuntimeException: Bad Code
        //System.out.println(z);
    }

    /*
        11. Given the AnotherBook class (in the zip file), declare a List typed for AnotherBook namely ‘books’
            with the following AnotherBook’s:
                a. title=”Gone with the wind”, genre=”Fiction”
                b. title=”Bourne Ultimatum”, genre=”Thriller”
                c. title=”The Client”, genre=”Thriller”
                Declare the following: List<String> genreList = new ArrayList<>();
                Stream books so that genreList refers to a List containing the genres of the books in the books List.

        (QID 2.1858)
     */
    private static void qid_q2_1858() {
        System.out.println("-------- qid_q2_1858");
        List<AnotherBook> books = Arrays.asList(
                new AnotherBook("Gone with the wind", "Fiction"),
                new AnotherBook("Bourne Ultimatum", "Thriller"),
                new AnotherBook("The Client", "Thriller")
        );

        List<String> genreList = new ArrayList<>();

        books.stream()
//                .map(book -> book.getGenre())     // lambda
                .map(AnotherBook::getGenre)       // method reference
//                .map(                           // anonymous inner class
//                    new Function(){
//                        @Override
//                        public String apply(Object o){
//                            return ((AnotherBook)o).getGenre();
//                        }
//                    }
//                )
//                .forEach(s -> genreList.add(s));    // lambda
                .forEach(genreList::add);         // method reference (bound)
//                  .forEach(ArrayList::add);         // does not compile!
//                      .forEach (                      // anonymous inner class
//                          new Consumer(){
//                              @Override
//                              public void accept(Object o){
//                                  genreList.add((String)o);
//                              }
//                          }
//                      );

        System.out.println(genreList);
    }

    /*
        12. There are two parts:
            a. Generate a DoubleStream using the of() method consisting of the numbers 0, 2 and 4. Note
            that this stream is a stream of primitives and not a stream of types. Filter in odd numbers only
            and sum the remaining stream. You should get 0.
            b. Using 1.0 and 3.0, generate a stream of Double’s. Map them to primitive double’s. Filter in
            even numbers only and calculate the average. Output the result without running the risk of
            generating an exception.

            (QID 2.2024)
     */
    private static void qid_q2_2024() {
        System.out.println("-------- qid_q2_2024");
        // a. sum()
        DoubleStream doubleStream = DoubleStream.of(0, 2, 4);
        double sum = doubleStream.filter(value -> value % 2 != 0)
                .sum();
        System.out.println("Sum is = " + sum);

        // b. average()
        Stream<Double> doubleStream1 = Stream.of(1.0, 3.0);
        OptionalDouble average = doubleStream1
                // DoubleStream mapToDouble(ToDoubleFunction)
                //      ToDoubleFunction
                //          double applyAsDouble(T value);
                .mapToDouble(d -> d.doubleValue())
                .filter(value -> value % 2 == 0)
                .average();
        if(average.isPresent()){
            System.out.println("average == " + average.getAsDouble());
        }
    }

    /*
        13. This question demonstrates lazy evaluation. Declare the following List<Integer> ls =
            Arrays.asList(11, 11, 22, 33, 33, 55, 66);
            a. stream the List (note that this is possible because List is a Collection and Collection defines a
                stream() method); ensure only distinct (unique) numbers are streamed; check if “any match”
                11. You should get true for this.
            b. stream the List again (this is necessary because once a stream is closed by a previous terminal
                operation, you must re-create the stream); check to see if “none match” the expression
                x%11>0. Note that the terminal operation noneMatch(Predicate) needs to return false for
                every element in the stream for noneMatch() to return true. In other words, “none of them
                match this….that’s correct, none of them do; return true”. You should get true here as well.

            (QID 2.1840)
     */
    private static void qid_q2_1840() {
        System.out.println("-------- qid_q2_2024");
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        ls.stream().distinct()
                .forEach(n -> System.out.println(n));  // 11, 22, 33, 55, 66
        // Java only generates the amount of stream you need (lazy evaluation).
        System.out.println("\nanyMatch");
        System.out.println(ls.stream()
                            .distinct()
                            .peek(System.out::println)
                            .anyMatch(x -> x == 11)); // true

        System.out.println("\nnoneMatch");
        System.out.println(ls.stream()
                .peek(System.out::println)
                .noneMatch(x -> x % 11 > 0));// true
    }

    /*
        14. Examine the following code. Note that an AtomicInteger is a version of Integer that is safe to use in
            concurrent (multi-threaded) environments. The method incrementAndGet() is similar to ++ai
            a) Why is the value of ai at the end 0 (and not 4)?

        (QID 2.1841)
     */
    private static void qid_q2_1841() {
        System.out.println("-------- qid_q2_1841");
        AtomicInteger ai = new AtomicInteger(); // initial value of 0
        Stream.of(11, 11, 22, 33)
                .parallel()
                .filter(n -> {
                    ai.incrementAndGet();
                    return n % 2 == 0;
                })
                .forEach(System.out::println); // 22
        System.out.println(ai); // 4

//        b) The following code generates an IllegalStateException. Fix the code.
//        AtomicInteger ai = new AtomicInteger(); // initial value of 0
//        Stream<Integer> stream = Stream.of(11, 11, 22, 33).parallel();
//        stream.filter(e -> {
//            ai.incrementAndGet();
//            return e % 2 == 0;
//        });
//        stream.forEach(System.out::println);// java.lang.IllegalStateException
//        System.out.println(ai);

        AtomicInteger ai2 = new AtomicInteger(); // initial value of 0
        Stream<Integer> stream = Stream.of(11, 11, 22, 33, 34).parallel();
        Stream<Integer> stream2 = stream.filter(e -> {
            ai2.incrementAndGet();
            return e % 2 == 0;
        });
        stream2.forEach(System.out::println);// 22
        System.out.println(ai2);
    }

}
