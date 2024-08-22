package lambdas.assignment.interfaces;

@FunctionalInterface
public interface Functionable<T, R> {

    R applyThis(T t);

}
