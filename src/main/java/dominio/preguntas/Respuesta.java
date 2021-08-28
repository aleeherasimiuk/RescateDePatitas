package dominio.preguntas;

import java.util.List;

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


  public boolean matcheaConAlguna(List<Respuesta> respuestas) {

    Respuesta respuestaQueMatchea = getPregunta().obtenerRespuestaQueMatchea(respuestas);
    if(respuestaQueMatchea == null) return true;
    
    return respuestaQueMatchea.getRespuesta().equalsIgnoreCase(getRespuesta());
    
  }
}