package dominio.preguntas;

public class Respuesta {
  private final Pregunta pregunta;
  private final String respuesta;

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

}