package dominio.repositorio;

import java.util.function.Predicate;

import dominio.util.Lista;

public abstract class Repositorio<T> {
  
  protected final Lista<T> repositorio;
  protected Repositorio(){
    repositorio = new Lista<T>();
  }

  public void registrar(T t){
    repositorio.add(t);
  }

  @SafeVarargs
  public final void registrar(T... t){ // Si, yo estoy igual de WTF que ustedes
    repositorio.add(t);
  }

  public void borrar(T t){
    repositorio.remove(t);
  }

  public T buscar(Predicate<T> condicion){
    return repositorio.find(condicion);
  }

  public Lista<T> filtrar(Predicate<T> condicion){
    return repositorio.filter(condicion);
  }

  public int cantidadRegistros(){
    return repositorio.size();
  }

  public int contar(Predicate<T> condicion){
    return repositorio.count(condicion);
  }

  public boolean existe(Predicate<T> condicion){
    return repositorio.contains(condicion);
  }
  
}
