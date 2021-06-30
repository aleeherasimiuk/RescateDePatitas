package dominio.tareas;

import dominio.adopcion.DarEnAdopcion;
import dominio.adopcion.SolicitudAdopcion;
import dominio.preguntas.Pregunta;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioAdopcion;

import java.util.List;

public class Matcher {
  private final SolicitudAdopcion solicitudAdopcion;

  public Matcher(SolicitudAdopcion solicitudAdopcion) {
    this.solicitudAdopcion = solicitudAdopcion;
  }

  public List<DarEnAdopcion> recomendaciones(){
    return RepositorioAdopcion.getInstance()
      .filtrar(publicacionDuenio -> this.matcheaCon(publicacionDuenio, solicitudAdopcion));
  }

  private boolean matcheaCon(DarEnAdopcion publicacionDuenio, SolicitudAdopcion solicitudAdopcion) {
    return solicitudAdopcion.getRespuestas()
          .stream()
          .filter(respuesta -> !respuesta.getPregunta().esAbierta())
          .allMatch(respuesta -> matcheaConAlguna(respuesta, publicacionDuenio.getRespuestas()));
  }

  private boolean matcheaConAlguna(Respuesta respuesta, List<Respuesta> respuestas) {

    Respuesta respuestaQueMatchea = obtenerRespuestaQueMatchea(respuesta.getPregunta(), respuestas);
    if(respuestaQueMatchea == null) return true;
    
    return respuestaQueMatchea.getRespuesta().equalsIgnoreCase(respuesta.getRespuesta());
    
  }

  private Respuesta obtenerRespuestaQueMatchea(Pregunta pregunta, List<Respuesta> respuestas) {
    return respuestas.stream()
        .filter(r -> r.getPregunta().equals(pregunta))
        .findFirst()
        .orElse(null);
  }
}
