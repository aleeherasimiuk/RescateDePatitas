package dominio.repositorio;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithEntityManager;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Entity;
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
    return query(entityManager -> {
      return ((Long) entityManager
        .createQuery("SELECT COUNT(e) FROM "+ getClassName().getSimpleName() + " e")
        .getSingleResult())
        .intValue();
    });
  }

  public int contar(Predicate<T> condicion){
    return paraTodos(list -> (int) list.stream().filter(condicion).count());
  }

  public void vaciar(){
    transaction(entityManager -> {
      entityManager.createQuery("DELETE FROM " + getClassName().getSimpleName())
        .executeUpdate();
    });
  }


  public List<T> todos(){
    return paraTodos(list -> list);
  }

  public <R> R paraTodos(Function<List<T>, R> function){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    List<T> list = (List<T>) entityManager.createQuery("SELECT e FROM " + getClassName().getSimpleName() + " e", getClassName())
      //.setParameter("table", getEntityName())
      .getResultList();
    System.out.println(list.size());
    R result = function.apply(list);
    entityManager.close();

    return result;
  }

  @Deprecated
  public void forEach(Consumer<T> consumer){
    todos().forEach(consumer);
  }

  protected void transaction(Consumer<EntityManager> consumer){

    query(entityManager -> {
      EntityTransaction transaction = entityManager.getTransaction();
      
      transaction.begin();
      consumer.accept(entityManager);
      transaction.commit();

      return null;
    });

    // EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    // EntityTransaction transaction = entityManager.getTransaction();
    // transaction.begin();
    // consumer.accept(entityManager);
    // transaction.commit();
    // entityManager.close();
  }

  protected <R> R query(Function<EntityManager, R> function){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    //EntityManager entityManager = entityManager();
    R result = function.apply(entityManager);
    entityManager.close();
    return result;
  }

  protected abstract Class<T> getClassName();
  
}
