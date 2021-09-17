package dominio.preguntas;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class PreguntaCerrada extends Pregunta {
  @ElementCollection
  @CollectionTable(name = "fotos", joinColumns=@JoinColumn(name="mascota_id"))
  private List<String> opciones;

  public PreguntaCerrada(String preguntaDuenio, String preguntaAdoptante, String ...opciones) {
    super(preguntaDuenio, preguntaAdoptante);
    this.opciones = Arrays.asList(opciones);
  }

  @Override
  public boolean esRespuestaValida(String respuesta) {
    return opciones.stream().anyMatch(r -> r.equalsIgnoreCase(respuesta));
  }

  @Override
  public boolean esAbierta() {
    return false;
  }
}
