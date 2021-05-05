package dominio.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<T> extends ArrayList<T> {

  public Lista(Collection<? extends T> c) {
    super(c);
  }

  public Lista() {
  }

  public Lista(int initialCapacity) {
    super(initialCapacity);
  }

  @SafeVarargs
  public Lista(T... elements) {
    for (T object : elements) {
      this.add(object);
    }
  }

  public Lista<T> filter(Predicate<T> condition) {

    Lista<T> nuevaLista = new Lista<T>();

    for (T t : this) {
      if (condition.test(t))
        nuevaLista.add(t);
    }

    return nuevaLista;
  }

  public <R> Lista<R> map(Function<T, R> mapper) {

    Lista<R> nuevaLista = new Lista<R>(this.size());

    for (T t : this) {
      nuevaLista.add(mapper.apply(t));
    }

    return nuevaLista;
  }

  public boolean contains(Predicate<T> condition) {
    return this.stream().anyMatch(condition);
  }

  public T find(Predicate<T> condition) {
    return this.stream().filter(condition).findFirst().orElse(null);
  }

  public T fold(BinaryOperator<T> accumulator) {
    return this.stream().reduce(accumulator).orElse(null);
  }

  public int count(Predicate<T> condition) {
    return (int) this.stream().filter(condition).count();
  }

}
