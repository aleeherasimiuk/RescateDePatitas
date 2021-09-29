package dominio.adopcion;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.preguntas.Respuesta;
import dominio.repositorio.RepositorioAdopcion;
import dominio.usuarios.Duenio;
import persistencia.PersistentEntity;
import servicios.mail.EmailException;
import servicios.mail.JavaMail;
import servicios.mail.MailAdopcion;

@Entity
@Table(name="publicacion_adopcion")
public class DarEnAdopcion extends PersistentEntity {
  @ManyToOne(cascade = CascadeType.MERGE)
  private Duenio duenio;
  @ManyToOne(cascade = CascadeType.MERGE)
  private Mascota mascota;
  @ManyToOne(cascade = CascadeType.MERGE)
  private Asociacion asociacion;

  @OneToMany(cascade = CascadeType.MERGE)
  @Column(name="respuesta")
  private List<Respuesta> respuestas;


  protected DarEnAdopcion() {}

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
    try{
      javaMail.enviarMail(mailer);
    } catch (EmailException e) {
      System.out.println("Error al enviar el mail de adopción" + e.getMessage());
      // Encolar para la próxima
    }
  }

  public void darDeBaja(){
    RepositorioAdopcion.getInstance().borrar(this);
  }



}
