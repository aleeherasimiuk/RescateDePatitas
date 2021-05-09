package dominio.personas;

import java.time.LocalDate;

public class DatosPersona {
  private String apellido;
  private String nombre;
  private Documento documento;
  private Contacto contacto;
  private LocalDate fechaNacimiento;

  public DatosPersona(String apellido, String nombre, Documento documento, Contacto contacto,
      LocalDate fechaNacimiento) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.documento = documento;
    this.contacto = contacto;
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public Documento getDocumento() {
    return documento;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public int getTelefono() {
    return contacto.getTelefono();
  }
}
