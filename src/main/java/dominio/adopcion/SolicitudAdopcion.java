package dominio.adopcion;

import dominio.asociacion.Asociacion;
import dominio.mascota.Tamanio;
import dominio.publicaciones.Respuesta;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

import java.util.List;

public class SolicitudAdopcion {
  private final List<Respuesta> respuestas;
  private final Asociacion asociacion;
  private final Duenio adoptante;

  public SolicitudAdopcion(Duenio adoptante,Asociacion asociacion, List<Respuesta> respuestas) {
    this.respuestas = respuestas;
    this.asociacion = asociacion;
    this.adoptante = adoptante;
  }

  public List<Respuesta> getRespuestas() {
    return respuestas;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public Duenio getAdoptante() {
    return adoptante;
  }

}
