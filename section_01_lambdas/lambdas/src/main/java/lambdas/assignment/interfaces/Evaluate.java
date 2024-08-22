package lambdas.assignment.interfaces;

@FunctionalInterface
public interface Evaluate<T> {

    boolean isNegative(T t);

}
