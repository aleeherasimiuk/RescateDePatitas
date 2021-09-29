package dominio.repositorio;


import dominio.usuarios.Admin;

public class RepositorioAdministradores extends Repositorio<Admin>{
  
  private static final RepositorioAdministradores INSTANCE = new RepositorioAdministradores();
  private RepositorioAdministradores(){};
  public static RepositorioAdministradores getInstance() {
    return INSTANCE;
  }
  
  @Override
  protected Class<Admin> getClassName() {
    return Admin.class;
  }
  
  

  

}
