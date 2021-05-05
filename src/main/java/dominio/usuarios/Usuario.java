package dominio.usuarios;

public class Usuario {
  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    new ValidadorContrasenia().validarContrasenia(contrasenia);
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
