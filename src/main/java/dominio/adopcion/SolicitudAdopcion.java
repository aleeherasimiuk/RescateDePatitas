package dominio.adopcion;

import dominio.asociacion.Asociacion;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioSolicitudesAdopcion;
import dominio.usuarios.Duenio;
import servicios.mail.EmailException;
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
    try{
      javaMail.enviarMail(mailer);
    } catch(EmailException e){
      System.out.println("Error al enviar el correo" + e.getMessage());
      // Encolar para la próxima
    }
  }

  public void darDeBaja(){
    RepositorioSolicitudesAdopcion.getInstance().borrar(this);
  }

  public boolean matcheaCon(DarEnAdopcion publicacionDuenio) {
    return getRespuestas()
          .stream()
          .filter(respuesta -> !respuesta.getPregunta().esAbierta())
          .allMatch(respuesta -> respuesta.matcheaConAlguna(publicacionDuenio.getRespuestas()));
  }

  public List<DarEnAdopcion> recomendaciones(){

    RepositorioAdopcion solicitudesDarEnAdopcion = RepositorioAdopcion.getInstance();

    return solicitudesDarEnAdopcion
      .filtrar(publicacionDuenio -> this.matcheaCon(publicacionDuenio));
  }

}
