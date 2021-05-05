package dominio.usuarios;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

  private String contrasenia;
  private String nombreUsuario;

  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    new ValidadorContrasenia().validarContrasenia(contrasenia);
    this.contrasenia = BCrypt.hashpw(contrasenia, BCrypt.gensalt());
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }
}
