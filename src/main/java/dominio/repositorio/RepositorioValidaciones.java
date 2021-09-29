package dominio.repositorio;

import org.hibernate.cfg.NotYetImplementedException;

import dominio.passwords.Validation;

public class RepositorioValidaciones extends Repositorio<Validation>{

  private static final RepositorioValidaciones INSTANCE = new RepositorioValidaciones();
  private RepositorioValidaciones(){}

  public void validatePassword(String password){
    //super.forEach((validacion) -> validacion.validatePassword(password));
  }

  public static RepositorioValidaciones getInstance(){
    return INSTANCE;
  }

  @Override
  protected Class<Validation> getClassName() {
    return Validation.class;
  }
  
}
