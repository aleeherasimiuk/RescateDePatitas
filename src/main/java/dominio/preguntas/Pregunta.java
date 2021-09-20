package dominio.preguntas;

import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import persistencia.PersistentEntity;

@Entity
@Table(name="preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pregunta")
public class Pregunta extends PersistentEntity {
  private String preguntaDuenio;
  private String preguntaAdoptante;

  protected Pregunta(){}

  public Pregunta(String preguntaDuenio, String preguntaAdoptante) {
    this.preguntaDuenio = preguntaDuenio;
    this.preguntaAdoptante = preguntaAdoptante;
  }

  public boolean esRespuestaValida(String respuesta) {
    return !respuesta.isEmpty();
  }

  public String getPreguntaDuenio() {
    return preguntaDuenio;
  }

  public String getPreguntaAdoptante() {
    return preguntaAdoptante;
  }

  public boolean esAbierta(){
    return true;
  }

  public boolean esOpcionMultiple(){
    return false;
  }

  public Respuesta obtenerRespuestaQueMatchea(List<Respuesta> respuestas) {
    return respuestas.stream()
        .filter(r -> r.getPregunta().equals(this))
        .findFirst()
        .orElse(null);
  }
}
