package dominio.personas;

public class Contacto {
  private String nombre;
  private String apellido;
  private int telefono;
  private String email;

  public Contacto(String nombre, String apellido, int telefono, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public int getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
