package dominio.rescate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import dominio.personas.DatosPersona;
import dominio.repositorio.RepositorioRescatesConChapita;
import persistencia.PersistentEntity;

@Entity
@Table(name="rescatistas")
public class Rescatista extends PersistentEntity{

  @OneToOne
  @JoinColumn(name = "datos_persona_id")
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
