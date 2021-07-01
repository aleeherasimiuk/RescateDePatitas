package dominio.exceptions;

public class RespuestaInvalida extends RuntimeException {
  public RespuestaInvalida(String preguntaDuenio) {
    super("Respuesta invalida para la pregunta: " + preguntaDuenio);
  }
}
