package dominio.repositorio;

import dominio.adopcion.SolicitudAdopcion;

public class RepositorioSolicitudesAdopcion extends Repositorio<SolicitudAdopcion>{

  private static final RepositorioSolicitudesAdopcion INSTANCE = new RepositorioSolicitudesAdopcion();

  private RepositorioSolicitudesAdopcion(){}

  public static RepositorioSolicitudesAdopcion getInstance() {
    return INSTANCE;
  }
  
}
