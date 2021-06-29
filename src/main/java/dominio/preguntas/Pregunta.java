package dominio.preguntas;

public class Pregunta {
  private String preguntaDuenio;
  private String preguntaAdoptante;

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
}
