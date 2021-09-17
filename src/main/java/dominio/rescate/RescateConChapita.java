package dominio.rescate;

import java.time.LocalDate;

import dominio.exceptions.HogarNoAceptaMascota;
import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.ubicacion.Coordenadas;
import persistencia.PersistentEntity;
import servicios.mail.EmailException;
import servicios.mail.JavaMail;
import servicios.mail.MailRescateConChapita;
import dominio.hogares.Hogar;

import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RescateConChapita extends PersistentEntity {

  @Embedded
  private DatosRescate datosRescate;

  @ManyToOne
  private Mascota mascota;

  protected RescateConChapita(){}

  public RescateConChapita(DatosRescate datosRescate, Mascota mascota) {
    this.datosRescate = datosRescate;
    this.mascota = mascota;
  }

  public void avisarAlDuenio(JavaMail javaMail){
    MailRescateConChapita mailer = new MailRescateConChapita(this);
    try{
      javaMail.enviarMail(mailer);
    } catch(EmailException e){
      System.out.println("Error al enviar el mail: " + e.getMessage());
      // Encolar para la próxima vez
    }
  }

  public void agregarUnaFoto(String url) {
    datosRescate.getFotos().add(url);
  }

  public Boolean sucedioDentroDeLosUltimos10Dias() {
    return Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), getFecha())) <= 10;
  }

  public void asignarHogar(Hogar hogar){
    if(!hogar.aceptaMascota(mascota.getClase(), mascota.getTamanio()))
      throw new HogarNoAceptaMascota();
    this.datosRescate.setHogar(hogar);
  }

  public Mascota getMascota() {
    return this.mascota;
  }

  public String getDescripcion() {
    return datosRescate.getDescripcion();
  }

  public Coordenadas getLugar() {
    return datosRescate.getLugar();
  }

  public int telefonoDeContacto() {
    return datosRescate.getRescatista().getTelefono();
  }

  public List<String> getFotos() {
    return datosRescate.getFotos();
  }

  public boolean laMascotaTieneChapita(){
    return mascota != null;
  }

  public LocalDate getFecha() {
    return datosRescate.getFecha();
  }

  public Hogar getHogar() {
    return datosRescate.getHogar();
  }

  public void setHogar(Hogar hogar) {
    datosRescate.setHogar(hogar);
  }

  public Contacto datosDeContacto(){
    return getDatosDeRescatista().getContacto();
  }

  public DatosPersona getDatosDeRescatista() {
    return datosRescate.getRescatista().getDatosPersona();
  }
}
