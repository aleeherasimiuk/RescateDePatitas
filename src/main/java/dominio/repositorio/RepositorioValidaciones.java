package dominio.repositorio;

import dominio.passwords.Validation;

public class RepositorioValidaciones extends Repositorio<Validation>{

  private static final RepositorioValidaciones INSTANCE = new RepositorioValidaciones();
  private RepositorioValidaciones(){}

  public void validatePassword(String password){
    
    todos().stream().filter(Validation::activada).forEach((validacion) -> validacion.validatePassword(password));
  }

  public static RepositorioValidaciones getInstance(){
    return INSTANCE;
  }

  @Override
  protected Class<Validation> getClassName() {
    return Validation.class;
  }
  
}
