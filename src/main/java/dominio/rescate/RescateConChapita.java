package dominio.rescate;

import java.time.LocalDate;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.personas.DatosPersona;
import dominio.ubicacion.Coordenadas;
import servicios.mail.JavaMail;
import servicios.mail.MailRescateConChapita;
import dominio.hogares.Hogar;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class RescateConChapita {

  private final DatosRescate datosRescate;

  private Mascota mascota;

  public RescateConChapita(DatosRescate datosRescate, Mascota mascota) {
    this.datosRescate = datosRescate;
    this.mascota = mascota;
  }

  public void avisarAlDuenio(JavaMail javaMail){
    MailRescateConChapita mailer = new MailRescateConChapita(this);
    javaMail.enviarMail(mailer);
  }

  public void agregarUnaFoto(String url) {
    datosRescate.getFotos().add(url);
  }

  public Boolean sucedioDentroDeLosUltimos10Dias() {
    return Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), getFecha())) <= 10;
  }

  public void asignarHogar(Hogar hogar){
    if(!hogar.aceptaMascota(mascota.getClase(), mascota.getTamanio()))
      throw new RuntimeException("El hogar solicitado no acepta a la mascota");
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

  public void setMascota(Mascota mascota) {
    this.mascota = mascota;
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
