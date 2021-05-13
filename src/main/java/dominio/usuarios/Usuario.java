package dominio.usuarios;

import org.mindrot.jbcrypt.BCrypt;

import dominio.repositorio.RepositorioValidaciones;

public abstract class Usuario {

  private String username;

  public String getUsername() {
    return username;
  }

  private String password;

  public String getPassword() {
    return password;
  }

  public Usuario(String username, String password) {
    this.username = username;
    validarPassword(password);
    this.password = encriptarPassword(password);
  }

  private void validarPassword(String password) {
    RepositorioValidaciones.getInstance().validatePassword(password);
  }

  private String encriptarPassword(String password) {
    return BCrypt.hashpw(password,BCrypt.gensalt(12));
  }
}
