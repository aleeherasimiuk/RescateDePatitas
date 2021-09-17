package dominio.personas;

import persistencia.PersistentEntity;
import persistencia.convertidores.ConvertidorLocalDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "personas")
public class DatosPersona extends PersistentEntity {
  private String apellido;
  private String nombre;
  @Embedded
  private Documento documento;
  @Embedded
  private Contacto contacto;
  @Convert(converter = ConvertidorLocalDate.class)
  private LocalDate fechaNacimiento;

  public DatosPersona(String apellido, String nombre, Documento documento, Contacto contacto,
      LocalDate fechaNacimiento) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.documento = documento;
    this.contacto = contacto;
    this.fechaNacimiento = fechaNacimiento;
  }

  public DatosPersona(){};

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
