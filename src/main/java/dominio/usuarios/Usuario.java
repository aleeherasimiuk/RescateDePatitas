package dominio.usuarios;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

  private String password;
  private String nombreUsuario;

  public Usuario(String nombreUsuario, String password) {
    this.nombreUsuario = nombreUsuario;
    ValidadorContrasenia.validarPassword(password);
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }
}
