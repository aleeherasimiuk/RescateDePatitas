package dominio.usuarios;

public class Usuario {
  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }
  private String contrasenia;
  private String nombreUsuario;

  public String getContrasenia() {
    return contrasenia;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }


  
}
