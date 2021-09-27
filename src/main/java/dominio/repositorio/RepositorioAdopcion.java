package dominio.repositorio;

import dominio.adopcion.DarEnAdopcion;

public class RepositorioAdopcion extends Repositorio<DarEnAdopcion>{

  private static final RepositorioAdopcion INSTANCE = new RepositorioAdopcion();

  private RepositorioAdopcion(){}

  public static RepositorioAdopcion getInstance() {
    return INSTANCE;
  }

}
