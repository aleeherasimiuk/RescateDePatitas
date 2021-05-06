package dominio.usuarios;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

  private String password;
  private String username;

  public Usuario(String username, String password) {
    this.username = username;
    PasswordValidator.validate(password);
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public String getPassword() {
    return password;
  }
  
  public String getUsername() {
    return username;
  }
}
