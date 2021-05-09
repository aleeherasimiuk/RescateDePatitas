package dominio.usuarios;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

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
    PasswordValidator.validate(password);
    this.password = BCrypt.hashpw(password,BCrypt.gensalt(12));
  }
}
