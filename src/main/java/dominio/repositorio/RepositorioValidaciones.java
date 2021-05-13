package dominio.repositorio;

import dominio.passwords.Validacion;

public class RepositorioValidaciones extends Repositorio<Validacion>{

  private static final RepositorioValidaciones INSTANCE = new RepositorioValidaciones();
  private RepositorioValidaciones(){}

  public void validatePassword(String password){
    super.repositorio.forEach((validacion) -> validacion.validatePassword(password));
  }

  public RepositorioValidaciones getInstance(){
    return INSTANCE;
  }
  
}
