package dominio.publicaciones;

import java.util.List;

import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class PublicacionDuenio {
  private final Duenio duenio;
  private final Mascota mascota;
  private final Asociacion asociacion;
  private final List<Respuesta> respuestas;

  public PublicacionDuenio(Duenio duenio, Mascota mascota, Asociacion asociacion, List<Respuesta> respuestas) {
    this.duenio = duenio;
    this.mascota = mascota;
    this.asociacion = asociacion;
    this.respuestas = respuestas;
  }

  public Duenio getDuenio() {
    return duenio;
  }
  public Mascota getMascota() {
    return mascota;
  }
  public Asociacion getAsociacion() {
    return asociacion;
  }
  public List<Respuesta> getRespuestas() {
    return respuestas;
  }

  public void adoptar(Duenio adoptante) {
    // TODO: notificar al duenio actual de la mascota
    duenio.removerMascota(mascota);
    adoptante.registrarUnaMascota(mascota);
  }
}
