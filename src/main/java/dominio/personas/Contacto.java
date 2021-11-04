package dominio.personas;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contacto {
  @Column(name = "nombre_contacto")
  private String nombre;
  @Column(name = "apellido_contacto")
  private String apellido;
  @Column(name = "telefono_contacto")
  private String telefono;
  @Column(name = "email_contacto")
  private String email;

  public Contacto(String nombre, String apellido, String telefono, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  protected Contacto(){}

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
