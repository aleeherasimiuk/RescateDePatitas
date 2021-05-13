package dominio.repositorio;

import dominio.usuarios.Administrador;

public class RepositorioAdministradores extends Repositorio<Administrador>{
  
  private static final RepositorioAdministradores INSTANCE = new RepositorioAdministradores();
  private RepositorioAdministradores(){};
  public static RepositorioAdministradores getInstance() {
    return INSTANCE;
  }

}
