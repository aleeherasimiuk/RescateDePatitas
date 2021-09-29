package dominio.adopcion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import dominio.asociacion.Asociacion;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioSolicitudesAdopcion;
import dominio.usuarios.Duenio;
import persistencia.PersistentEntity;
import servicios.mail.EmailException;
import servicios.mail.JavaMail;
import servicios.mail.MailRecomendacion;

@Entity
@Table(name="solicitud_adopcion")
public class SolicitudAdopcion extends PersistentEntity{
  @OneToMany(cascade=CascadeType.ALL)
  private List<Respuesta> respuestas;
  @ManyToOne(cascade = CascadeType.MERGE)
  private Asociacion asociacion;
  @OneToOne
  private Duenio adoptante;

  protected SolicitudAdopcion(){}

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
