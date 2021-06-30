package dominio.publicaciones;

import dominio.mascota.Mascota;
import dominio.preguntas.MatchWithField;
import dominio.preguntas.Pregunta;

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

  public boolean matcheaConAlguna(Mascota mascota, List<Respuesta> respuestas) {
    Respuesta respuesta = obtenerRespuestaQueMatchea(respuestas);
    MatchWithField matchWithField = respuesta.getPregunta().getMatchWithField();
    switch (matchWithField){
      case PREGUNTA:
        return respuesta.getRespuesta().equals(this.getRespuesta());
      case CLASE_MASCOTA:
        return respuesta.getRespuesta().equals(mascota.)
    }
  }

  private Respuesta obtenerRespuestaQueMatchea(List<Respuesta> respuestas) {
    return respuestas.stream()
        .filter(r -> r.getPregunta().equals(pregunta))
        .findFirst()
        .orElse(null);
  }

}