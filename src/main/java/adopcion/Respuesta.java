package adopcion;

import dominio.asociacion.Pregunta;

public class Respuesta {
  private final Pregunta preguntaAsociada;
  private final String respuesta;
  
  public Respuesta(Pregunta preguntaAsociada, String respuesta) {
    super();
    this.preguntaAsociada = preguntaAsociada;
    this.respuesta = respuesta;
  }
}
