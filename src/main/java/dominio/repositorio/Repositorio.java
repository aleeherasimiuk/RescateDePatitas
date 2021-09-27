package dominio.repositorio;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class Repositorio<T>{
  
  public void registrar(T t){
    transaction(entityManager -> entityManager.persist(t));
  }

  @SafeVarargs
  public final void registrar(T... t){
    transaction(entityManager -> {
      for (T objects : t) {
        entityManager.persist(objects);
      }
    });
  }

  public void borrar(T t){
    transaction(entityManager -> entityManager.remove(t));
  }

  public List<T> filtrar(Predicate<T> condicion){
    return paraTodos(list -> list.stream().filter(condicion).collect(Collectors.toList()));
  }

  public int cantidadRegistros(){
    return paraTodos(list -> list.size());
  }

  public int contar(Predicate<T> condicion){
    return paraTodos(list -> (int) list.stream().filter(condicion).count());
  }

  public void vaciar(){
    transaction(entityManager -> {});
  }


  public List<T> todos(){
    return paraTodos(list -> list);
  }
  
  @SuppressWarnings("unchecked")
  public <R> R paraTodos(Function<List<T>, R> function){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    List<T> list = (List<T>) entityManager.createQuery("from :table")
      .setParameter("table", getClass())
      .getResultList();

    R result = function.apply(list);
    entityManager.close();

    return result;
  }

  @Deprecated
  public void forEach(Consumer<T> consumer){
    todos().forEach(consumer);
  }

  protected void transaction(Consumer<EntityManager> consumer){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    consumer.accept(entityManager);
    transaction.commit();
    entityManager.close();
  }
  
}
