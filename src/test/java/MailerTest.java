import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.adopcion.DarEnAdopcion;
import dominio.adopcion.DarEnAdopcionBuilder;
import dominio.adopcion.SolicitudAdopcion;
import dominio.adopcion.SolicitudAdopcionBuilder;
import dominio.asociacion.Asociacion;
import dominio.mascota.Mascota;
import dominio.preguntas.Pregunta;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioDuenios;
import dominio.rescate.RescateConChapita;
import dominio.rescate.RescateSinChapita;
import dominio.usuarios.Duenio;
import servicios.mail.MailRecomendacion;
import servicios.mail.MailRescateConChapita;
import servicios.mail.MailRescateSinChapita;

class MailerTest {
  private static Fixture fixture = new Fixture();
  
  private static RescateConChapita rescateConChapita;
  private static MailRescateConChapita mailRescateConChapita;
  private static MailRescateSinChapita mailRescateSinChapita;
  private static RescateSinChapita publicacionMascotaUTN;
  
  private static MailRecomendacion mailRecomendacion;
  
  private static Duenio carlos;
  private static Duenio samuel;
  private static Duenio sabato;  
  private static Mascota pupi;
  private static Asociacion asociacion;
  
//  private static RescateSinChapita publicacionMascotaUTN;
  private static DarEnAdopcion publicacionSabatoDaEnAdopcionAPupi;
  private static List<DarEnAdopcion> publicaciones;
  
  @BeforeEach
  void setup() {
    rescateConChapita = fixture.getRescatePupi();
    publicacionMascotaUTN = fixture.getPublicacionUTN();
    mailRescateConChapita = new MailRescateConChapita(rescateConChapita);
    mailRescateSinChapita = new MailRescateSinChapita(publicacionMascotaUTN);    
    //publicacionSabatoDaEnAdopcionAPupi = fixture.publicacionSabatoDaEnAdopcionAPupi();
    
    carlos = fixture.getCarlos();
    sabato = fixture.getSabato();
    pupi = fixture.getPupi();
    carlos.registrarUnaMascota(fixture.getPupi());
    samuel = fixture.getSamuel();
    asociacion = fixture.getColaDeGato();
    
    //publicaciones.add(publicacionSabatoDaEnAdopcionAPupi);
  }
  
  @Test 
  void mailRecomendacionMensajeValido() {
    //mailRecomendacion = new MailRecomendacion(sabato, publicaciones); 
    //System.out.println(mailRecomendacion.generarMail().getMensaje());
    
  }
  
  @Test
  void rescatistaDePupiMensajeValido() {
    String mensaje = "Hola! Roberto\nEstamos muy contentos de anunciarte que encontramos al dueño de una mascota que encontraste\n\nRoberto Gimenez se contactará con vos para continuar con el proceso";
    mailRescateSinChapita = new MailRescateSinChapita(publicacionMascotaUTN);
    assertEquals(mensaje, mailRescateSinChapita.generarMail().getMensaje());    
  }
  
  @Test
  void rescatistaDePupiTieneAsuntoValido() {
    String asunto = "Buenas noticias!. Identificamos al dueño de una mascota que encontraste";
    mailRescateSinChapita = new MailRescateSinChapita(publicacionMascotaUTN);
    assertEquals(asunto, mailRescateSinChapita.generarMail().getAsunto());
  }
  
  @Test
  void rescatistaDePupiTieneDestinatarioValido() {
    mailRescateSinChapita = new MailRescateSinChapita(publicacionMascotaUTN);
    String destinatario = "robertito@gmail.com";
    assertEquals(destinatario, mailRescateConChapita.generarMail().getDestinatario());
    //System.out.println(mailRescateSinChapita.generarMail().getDestinatario());    
  }
  
  @Test
  void mailRescatePupiMensajeValido() {
    String mensaje = "Hola! Federico\nEstamos muy contentos de anunciarte que encontramos tu mascota!\nPupi fue encontrada por Roberto\n\nRescatista: Roberto Gimenez\nTeléfono: 1180700543\nEmail robertito@gmail.com\nDescripción en la que se encontró la mascota: parece ser un gato siames";
    assertEquals(mensaje, mailRescateConChapita.generarMail().getMensaje());
  }
  
  @Test
  void mailRescatePupiAsuntoValido() {
    String asunto = "Tenemos buenas noticias!. Encontramos a Pupi";
    assertEquals(asunto, mailRescateConChapita.generarMail().getAsunto());
  }
  
  @Test
  void mailRescatePupiDestinatariolValido() {
    String destinatario = "robertito@gmail.com";
    assertEquals(destinatario, mailRescateConChapita.generarMail().getDestinatario());
  }
  
}
