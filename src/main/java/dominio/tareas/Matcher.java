package dominio.tareas;

import dominio.adopcion.SolicitudAdopcion;
import dominio.preguntas.Pregunta;
import dominio.publicaciones.PublicacionDuenio;
import dominio.publicaciones.Respuesta;
import dominio.repositorio.RepositorioPublicacionesAdopcion;

import java.util.List;

public class Matcher {
  private final SolicitudAdopcion solicitudAdopcion;

  public Matcher(SolicitudAdopcion solicitudAdopcion) {
    this.solicitudAdopcion = solicitudAdopcion;
  }

  public List<PublicacionDuenio> recomendaciones(){
    return RepositorioPublicacionesAdopcion.getInstance()
      .filtrar(publicacionDuenio -> this.matcheaCon(publicacionDuenio, solicitudAdopcion));
  }

  private boolean matcheaCon(PublicacionDuenio publicacionDuenio, SolicitudAdopcion solicitudAdopcion) {
    return solicitudAdopcion.getRespuestas()
          .stream()
          .filter(respuesta -> !respuesta.getPregunta().esAbierta())
          .allMatch(respuesta -> matcheaConAlguna(respuesta, publicacionDuenio.getRespuestas()));
  }

  private boolean matcheaConAlguna(Respuesta respuesta, List<Respuesta> respuestas) {

    Respuesta respuestaQueMatchea = obtenerRespuestaQueMatchea(respuesta.getPregunta(), respuestas);
    if(respuestaQueMatchea == null) return true;
    
    return respuestaQueMatchea.getRespuesta().equals(respuesta.getRespuesta());
    
  }

  private Respuesta obtenerRespuestaQueMatchea(Pregunta pregunta, List<Respuesta> respuestas) {
    return respuestas.stream()
        .filter(r -> r.getPregunta().equals(pregunta))
        .findFirst()
        .orElse(null);
  }
}
