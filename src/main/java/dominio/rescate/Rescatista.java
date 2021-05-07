package dominio.rescate;

import java.time.LocalDate;

import dominio.personas.Contacto;
import dominio.personas.Persona;
import dominio.personas.TipoDeDocumento;
import dominio.repositorio.RepositorioRescates;

public class Rescatista extends Persona {

  private String direccion;

  public Rescatista(String apellido, String nombre, TipoDeDocumento tipoDocumento, int numeroDocumento,
      Contacto contacto, LocalDate fechaNacimiento, String direccion) {
    super(apellido, nombre, tipoDocumento, numeroDocumento, contacto, fechaNacimiento);
    this.direccion = direccion;
  }

  public String getDireccion() {
    return direccion;
  }

  public void registrarRescate(Rescate rescate){
    RepositorioRescates.getINSTANCE().registrar(rescate);
  }
}
