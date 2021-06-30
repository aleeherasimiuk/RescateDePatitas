package dominio.tareas;

import dominio.adopcion.SolicitudAdopcion;
import dominio.publicaciones.PublicacionDuenio;
import dominio.repositorio.RepositorioPublicacionesAdopcion;

import java.util.List;

public class Matcher {
  private final SolicitudAdopcion solicitudAdopcion;

  public Matcher(SolicitudAdopcion solicitudAdopcion) {
    this.solicitudAdopcion = solicitudAdopcion;
  }

  public List<PublicacionDuenio> recomendaciones(){
    return RepositorioPublicacionesAdopcion.getInstance()
      .filtrar(publicacionDuenio -> publicacionDuenio.matcheaCon(solicitudAdopcion));
  }
}
