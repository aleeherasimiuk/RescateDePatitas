package dominio.adopcion;

import dominio.asociacion.Asociacion;
import dominio.preguntas.Respuesta;
import dominio.usuarios.Duenio;
import servicios.mail.JavaMail;
import servicios.mail.MailRecomendacion;

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

  public void recomendar(List<DarEnAdopcion> recomendaciones, JavaMail javaMail) {
    MailRecomendacion mailer = new MailRecomendacion(adoptante, recomendaciones);
    javaMail.enviarMail(mailer);
  }

}
