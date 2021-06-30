package dominio.repositorio;

import dominio.publicaciones.PublicacionDuenio;

public class RepositorioPublicacionesAdopcion extends Repositorio<PublicacionDuenio>{

  private static final RepositorioPublicacionesAdopcion INSTANCE = new RepositorioPublicacionesAdopcion();

  private RepositorioPublicacionesAdopcion(){}

  public static RepositorioPublicacionesAdopcion getInstance() {
    return INSTANCE;
  }
}
