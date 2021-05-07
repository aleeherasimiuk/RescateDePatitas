package dominio.repositorio;

public class RepositorioAdministradores /*extends Repositorio<Administrador>*/{
  
  private static final RepositorioAdministradores INSTANCE = new RepositorioAdministradores();
  private RepositorioAdministradores(){};
  public static RepositorioAdministradores getInstance() {
    return INSTANCE;
  }

}
