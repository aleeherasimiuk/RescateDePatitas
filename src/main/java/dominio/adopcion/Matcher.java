package dominio.adopcion;

import dominio.publicaciones.PublicacionDuenio;
import dominio.repositorio.Repositorio;
import dominio.util.Lista;

import java.util.List;

public class Matcher {
  private final SolicitudAdopcion solicitudAdopcion;

  public Matcher(SolicitudAdopcion solicitudAdopcion) {
    this.solicitudAdopcion = solicitudAdopcion;
  }

  public List<PublicacionDuenio> recomendaciones(){
    Lista<PublicacionDuenio> publicaciones = new Lista<>();
    //RepositorioPublicacionesDeAdopcion.getInstance().publicaciones
    Lista<PublicacionDuenio> publicaciones2;
    publicaciones2.filter(publicacionDuenio -> publicacionDuenio.matcheaCon(solicitudAdopcion));
  }
}
