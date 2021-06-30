package dominio.adopcion;

import java.util.List;
import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.preguntas.Respuesta;
import dominio.usuarios.Duenio;
import servicios.mail.JavaMail;
import servicios.mail.MailAdopcion;

public class DarEnAdopcion {
  private final Duenio duenio;
  private final Mascota mascota;
  private final Asociacion asociacion;
  private final List<Respuesta> respuestas;

  public DarEnAdopcion(Duenio duenio, Mascota mascota, Asociacion asociacion, List<Respuesta> respuestas) {
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

  public void adoptar(Duenio adoptante, JavaMail javaMail) {
    duenio.removerMascota(mascota);
    adoptante.registrarUnaMascota(mascota);

    MailAdopcion mailer = new MailAdopcion(this, adoptante);
    javaMail.enviarMail(mailer);
  }



}
