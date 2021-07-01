package dominio.exceptions;

public class HayPreguntasSinResponder extends RuntimeException {
  public HayPreguntasSinResponder() {
    super("No puede crear la adopcion porque hay preguntas sin responder.");
  }
}
