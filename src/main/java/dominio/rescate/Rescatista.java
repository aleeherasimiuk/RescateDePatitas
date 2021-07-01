package dominio.rescate;

import dominio.personas.DatosPersona;
import dominio.repositorio.RepositorioRescatesConChapita;

public class Rescatista {

  private DatosPersona datosPersona;

  public DatosPersona getDatosPersona() {
    return datosPersona;
  }

  private String direccion;

  public String getDireccion() {
    return direccion;
  }

  public Rescatista(DatosPersona datosPersona, String direccion) {
    this.datosPersona = datosPersona;
    this.direccion = direccion;
  }

  public void registrarRescate(RescateConChapita rescate){
    RepositorioRescatesConChapita.getINSTANCE().registrar(rescate);
  }

  public int getTelefono() {
    return datosPersona.getTelefono();
  }
}
