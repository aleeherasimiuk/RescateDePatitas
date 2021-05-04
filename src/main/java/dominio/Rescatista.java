package dominio;

import java.time.LocalDate;

import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;

public class Rescatista extends Persona{

  private String direccion;

  public Rescatista(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento,
      Contacto contacto, LocalDate fechaNacimiento, String direccion) {
    super(apellido, nombre, tipoDocumento, numeroDocumento, contacto, fechaNacimiento);
    this.direccion = direccion;
  }

  
}