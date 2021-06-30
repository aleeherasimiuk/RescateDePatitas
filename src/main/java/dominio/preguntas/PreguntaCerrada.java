package dominio.preguntas;

import java.util.Arrays;
import java.util.List;

public class PreguntaCerrada extends Pregunta {
  private List<String> opciones;

  public PreguntaCerrada(String preguntaDuenio, String preguntaAdoptante, String ...opciones) {
    super(preguntaDuenio, preguntaAdoptante);
    this.opciones = Arrays.asList(opciones);
  }

  @Override
  public boolean esRespuestaValida(String respuesta) {
    return opciones.contains(respuesta);
  }

  @Override
  public boolean esAbierta() {
    return false;
  }
}
