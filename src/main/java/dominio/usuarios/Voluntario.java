package dominio.usuarios;

import dominio.repositorio.RepositorioPublicaciones;
import dominio.rescate.Publicacion;

import java.util.List;

public class Voluntario extends Usuario{
  public Voluntario(String username, String password){
    super(username,password);
  }

  public void aprobarPublicacion(Publicacion publicacion){
    publicacion.aprobar();
  }

  public List<Publicacion> obtenerPublicacionesPendientes(){
    return RepositorioPublicaciones.getINSTANCE().publicacionesPendientes();
  }

  public List<Publicacion> obtenerPublicacionesAprobadas(){
    return RepositorioPublicaciones.getINSTANCE().publicacionesAprobadas();
  }
}
