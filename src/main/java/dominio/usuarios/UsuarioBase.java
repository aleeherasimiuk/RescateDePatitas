package dominio.usuarios;

public abstract class UsuarioBase {
  public UsuarioBase(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }
  private String contrasenia;
  private String nombreUsuario;
}
