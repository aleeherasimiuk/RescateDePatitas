package dominio.repositorio;

import java.util.function.Consumer;
import java.util.function.Predicate;

import dominio.util.Lista;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public abstract class Repositorio<T> {
  
  protected final Lista<T> repositorio;
  protected Repositorio(){
    repositorio = new Lista<T>();
  }

  public EntityManager conectar(){
    EntityManager entityManager n= PerThreadEntityManagers.getEntityManager();
    return entityManager;
  }

  public void registrar(T t){
    EntityManager entityManager = conectar();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(t);
    transaction.commit();
    entityManager.close();
  }

  @SafeVarargs
  public final void registrar(T... t){ // Si, yo estoy igual de WTF que ustedes
    repositorio.add(t);
  }

  public void borrar(T t){
    EntityManager entityManager = conectar();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.remove(t);
    transaction.commit();
    entityManager.close();
  }

  public T buscar(Predicate<T> condicion){

    return repositorio.find(condicion);
  }

  public Lista<T> filtrar(Predicate<T> condicion){
    return repositorio.filter(condicion);
  }

  public int cantidadRegistros(){
    EntityManager entityManager = conectar();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    Query query = entityManager.createQuery("from ?1").setParameter(getClass().toString());
    transaction.commit();
    entityManager.close();
    return query.getResultList().size();
  }

  public int contar(Predicate<T> condicion){
    return repositorio.count(condicion);
  }

  public boolean existe(Predicate<T> condicion){
    return repositorio.contains(condicion);
  }

  public void vaciar(){
    repositorio.clear();
  }

  public Lista<T> todas(){
    return repositorio;
  }

  public void forEach(Consumer<T> consumer){
    repositorio.forEach(consumer);
  }
  
}
