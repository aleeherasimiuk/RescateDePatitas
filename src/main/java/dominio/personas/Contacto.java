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
  private Integer telefono;
  @Column(name = "email_contacto")
  private String email;

  public Contacto(String nombre, String apellido, Integer telefono, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  public Contacto(){}

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
