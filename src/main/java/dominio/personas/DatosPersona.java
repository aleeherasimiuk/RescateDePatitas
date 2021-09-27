package dominio.personas;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import persistencia.PersistentEntity;
import persistencia.convertidores.ConvertidorLocalDate;

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
  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  public DatosPersona(String apellido, String nombre, Documento documento, Contacto contacto,
      LocalDate fechaNacimiento) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.documento = documento;
    this.contacto = contacto;
    this.fechaNacimiento = fechaNacimiento;
  }

  protected DatosPersona(){};

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
