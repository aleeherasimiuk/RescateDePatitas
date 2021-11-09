package dominio.tareas;

import org.mindrot.jbcrypt.BCrypt;

import dominio.repositorio.RepositorioValidaciones;

public class ValidadorPassword {

  public void validarPassword(String password) {
    RepositorioValidaciones.getInstance().validatePassword(password);
  }

  public String encriptarPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }
  
  public Boolean login(String raw, String password) {
    return BCrypt.checkpw(raw, password);
  }
}
