package dominio.repositorio;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

public abstract class Repositorio<T>{
  
  public void registrar(T t){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    entityManager.persist(t);
  }

  @SafeVarargs
  public final void registrar(T... t){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    for (T objects : t) {
      entityManager.persist(objects);
    }
    
  }

  public void borrar(T t){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    entityManager.remove(t);
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

  public void vaciar() {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    entityManager.createQuery("DELETE FROM " + getClassName().getSimpleName()).executeUpdate();
  }


  public List<T> todos(){
    return paraTodos(list -> list);
  }

  public <R> R paraTodos(Function<List<T>, R> function) {
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    List<T> list = (List<T>) entityManager
        .createQuery("SELECT e FROM " + getClassName().getSimpleName() + " e", getClassName())
        // .setParameter("table", getEntityName())
        .getResultList();
    R result = function.apply(list);
    // entityManager.close();

    return result;
  }

  @Deprecated
  public void forEach(Consumer<T> consumer){
    todos().forEach(consumer);
  }


  protected <R> R query(Function<EntityManager, R> function){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    //EntityManager entityManager = entityManager();
    R result = function.apply(entityManager);
    //entityManager.close();
    return result;
  }

  public T buscarPorId(Long id){
    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    return entityManager.find(getClassName(), id);
  }

  protected abstract Class<T> getClassName();
  
}
