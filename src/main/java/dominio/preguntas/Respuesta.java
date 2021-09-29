package dominio.preguntas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import persistencia.PersistentEntity;

@Entity
@Table(name="respuestas")
public class Respuesta extends PersistentEntity {
  @ManyToOne(cascade=CascadeType.MERGE)
  private Pregunta pregunta;
  private String respuesta;

  protected Respuesta() {}

  public Respuesta(Pregunta pregunta, String respuesta) {
    this.pregunta = pregunta;
    this.respuesta = respuesta;
  }

  public Pregunta getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }


  public boolean matcheaConAlguna(List<Respuesta> respuestas) {

    Respuesta respuestaQueMatchea = getPregunta().obtenerRespuestaQueMatchea(respuestas);
    if(respuestaQueMatchea == null) return true;

    return respuestaQueMatchea.getRespuesta().equalsIgnoreCase(getRespuesta());

  }
}