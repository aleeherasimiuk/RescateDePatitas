package dominio.usuarios;

public class Usuario {

  private String contrasenia;
  private String nombreUsuario;

  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    new ValidadorContrasenia().validarContrasenia(contrasenia);
    this.contrasenia = contrasenia;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

}
