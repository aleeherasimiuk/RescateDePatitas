package dominio.rescate;

import java.time.LocalDate;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.Ubicacion.Coordenadas;
import dominio.hogares.Hogar;
import servicios.MailerDuenio;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class Rescate {

  private final DatosRescate datosRescate;

  private Mascota mascota;

  public Rescate(DatosRescate datosRescate, Mascota mascota) {
    this.datosRescate = datosRescate;
    this.mascota = mascota;
  }

  public void avisarAlDuenio(){
    MailerDuenio mail = new MailerDuenio();
    mail.enviarMail(this);
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

  public String emailDeContacto(){
    return datosRescate.getRescatista().getDatosPersona().getContacto().getEmail();
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

  public Contacto datosDeContacto(){
    return datosRescate.getRescatista().getDatosPersona().getContacto();
  }

  public Hogar getHogar() {
    return datosRescate.getHogar();
  }

  public void setHogar(Hogar hogar) {
    datosRescate.setHogar(hogar);
  }
}
