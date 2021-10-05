package dominio.preguntas;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class PreguntaCerrada extends Pregunta {
  @ElementCollection
  @CollectionTable(name = "opcion", joinColumns=@JoinColumn(name="pregunta_id"))
  @Column(name="opcion")
  //@OnDelete(action = OnDeleteAction.CASCADE)
  //@Cascade(org.hibernate.annotations.CascadeType.ALL)
  //@OnDelete(action = OnDeleteAction.CASCADE)
  //@Cascade(value={CascadeType.ALL})
  private List<String> opciones;

  public PreguntaCerrada(boolean global, String preguntaDuenio, String preguntaAdoptante, String ...opciones) {
    super(preguntaDuenio, preguntaAdoptante, global);
    this.opciones = Arrays.asList(opciones);
  }

  public PreguntaCerrada(String preguntaDuenio, String preguntaAdoptante, String ...opciones) {
    super(preguntaDuenio, preguntaAdoptante, false);
    this.opciones = Arrays.asList(opciones);
  }

  protected PreguntaCerrada() {}

  @Override
  public boolean esRespuestaValida(String respuesta) {
    return opciones.stream().anyMatch(r -> r.equalsIgnoreCase(respuesta));
  }

  @Override
  public boolean esAbierta() {
    return false;
  }
}
