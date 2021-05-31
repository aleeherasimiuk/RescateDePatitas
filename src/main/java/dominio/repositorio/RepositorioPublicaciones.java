package dominio.repositorio;

import dominio.rescate.Publicacion;
import dominio.util.Lista;

public class RepositorioPublicaciones extends Repositorio<Publicacion>{
  private static final RepositorioPublicaciones INSTANCE = new RepositorioPublicaciones();

  private RepositorioPublicaciones() {}

  public Lista<Publicacion> publicacionesAprobadas(){
    return repositorio.filter(Publicacion::estaAprobada);
  } 

  public Lista<Publicacion> publicacionesPendientes(){
    return repositorio.filter(Publicacion::estaPendiente);
  }

  public static RepositorioPublicaciones getINSTANCE(){
    return INSTANCE;
  } 
}
