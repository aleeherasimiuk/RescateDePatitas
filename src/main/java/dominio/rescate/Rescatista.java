package dominio.rescate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import dominio.personas.DatosPersona;
import dominio.repositorio.RepositorioRescatesConChapita;
import persistencia.PersistentEntity;

@Entity
public class Rescatista extends PersistentEntity{

  @OneToOne
  private DatosPersona datosPersona;

  protected Rescatista(){}

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
