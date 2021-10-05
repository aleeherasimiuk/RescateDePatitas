import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import dominio.adopcion.DarEnAdopcion;
import dominio.repositorio.RepositorioAdopcion;
import dominio.repositorio.RepositorioAsociaciones;
import dominio.repositorio.RepositorioCaracteristicas;
import dominio.repositorio.RepositorioDuenios;
import dominio.repositorio.RepositorioMascotas;
import dominio.repositorio.RepositorioPreguntas;
import dominio.repositorio.RepositorioRescatesConChapita;
import dominio.repositorio.RepositorioRescatesSinChapita;
import dominio.repositorio.RepositorioRescatistas;
import dominio.repositorio.RepositorioRespuestas;
import dominio.repositorio.RepositorioSolicitudesAdopcion;
import dominio.rescate.RescateConChapita;
import dominio.rescate.RescateSinChapita;
import dominio.usuarios.Duenio;
import servicios.mail.Mail;
import servicios.mail.MailAdopcion;
import servicios.mail.MailRecomendacion;
import servicios.mail.MailRescateConChapita;
import servicios.mail.MailRescateSinChapita;



class MailerTest {
  private static Fixture fixture = new Fixture();

  private static MailRescateConChapita mailRescateConChapita;
  private static MailRescateSinChapita mailRescateSinChapita;
  private static MailAdopcion mailAdopcion;
  private static MailRecomendacion mailRecomendacion;

  private static RescateConChapita rescateConChapita;
  private static RescateSinChapita publicacionMascotaUTN;
  
  private static Duenio sabato;  
  
  private static DarEnAdopcion publicacionSabatoDaEnAdopcionAPupi;
  private static List<DarEnAdopcion> publicaciones;
  
  @BeforeAll
  static void setup() {
    publicaciones = new ArrayList<>();
    rescateConChapita = fixture.getRescatePupi();
    publicacionMascotaUTN = fixture.getPublicacionUTN();
    mailRescateConChapita = new MailRescateConChapita(rescateConChapita);
    mailRescateSinChapita = new MailRescateSinChapita(publicacionMascotaUTN);    
    publicacionSabatoDaEnAdopcionAPupi = fixture.publicacionSabatoDaEnAdopcionAPupi();
  
    sabato = fixture.getSabato();
    //carlos.registrarUnaMascota(fixture.getPupi());
    
    publicaciones.add(publicacionSabatoDaEnAdopcionAPupi);
  }

  @AfterAll
  static void tearDown() {
    RepositorioRescatesConChapita.getINSTANCE().vaciar();
    RepositorioRescatesSinChapita.getINSTANCE().vaciar();
    RepositorioAdopcion.getInstance().vaciar();
    RepositorioRespuestas.getInstance().vaciar();
    RepositorioAsociaciones.getInstance().vaciar();
    RepositorioMascotas.getINSTANCE().vaciar();
    RepositorioDuenios.getInstance().vaciar();
    RepositorioSolicitudesAdopcion.getInstance().vaciar();
    RepositorioCaracteristicas.getINSTANCE().vaciar();
    PerThreadEntityManagers.getEntityManager().getTransaction().begin();
    PerThreadEntityManagers.getEntityManager().createNativeQuery("DELETE FROM OPCION").executeUpdate();
    PerThreadEntityManagers.getEntityManager().createNativeQuery("DELETE FROM RESCATES").executeUpdate();
    PerThreadEntityManagers.getEntityManager().getTransaction().commit();
    RepositorioPreguntas.getInstance().vaciar();
    RepositorioRescatistas.getInstance().vaciar();
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
    Mail mail = mailRescateSinChapita.generarMail();
    assertEquals(destinatario, mail.getDestinatario());    
  }

  @Test
  void mailRescatePupiMensajeValido() {
    String mensaje = "Hola! Federico\nEstamos muy contentos de anunciarte que encontramos tu mascota!\nPupi fue encontrada por Roberto\n\nRescatista: Roberto Gimenez\nTeléfono: 1180700543\nEmail robertito@gmail.com\nDescripción en la que se encontró la mascota: parece ser un gato siames";
    Mail mail = mailRescateConChapita.generarMail();
    assertEquals(mensaje, mail.getMensaje());
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

  @Test 
  void mailRecomendacionMensajeValido() {
    String mensaje ="Hola! Federico\nEstamos muy contentos de anunciarte que encontramos tu mascota!\nPupi fue encontrada por Roberto\n\nRescatista: Roberto Gimenez\nTeléfono: 1180700543\nEmail robertito@gmail.com\nDescripción en la que se encontró la mascota: parece ser un gato siames";
    mailRecomendacion = new MailRecomendacion(sabato, publicaciones);
    assertEquals(mensaje, mailRescateConChapita.generarMail().getMensaje());        
  }

  @Test
  void mailRecomendacionAsuntoValido() {
    String asunto ="¡Hey!. ¡Tenemos algunas recomendaciones para vos!";
    mailRecomendacion = new MailRecomendacion(sabato, publicaciones);
    assertEquals(asunto, mailRecomendacion.generarMail().getAsunto());        
  }

  @Test
  void mailRecomendacionDestinatarioValido() {
    String destinatario = "fedebal@gmail.com";
    mailRecomendacion = new MailRecomendacion(sabato, publicaciones);
    assertEquals(destinatario, mailRecomendacion.generarMail().getDestinatario());        
  }

  @Test
  void mailDeAdopcionMensajeValido() {
    String mensaje ="Hemos encontrado un adpotante para tu mascota\nSabato desea adoptar a tu mascotaPuedes contactarte mediante los siguientes medios: \nTelefono: 1180700542\nEmail: fedebal@gmail.com\n";
    mailAdopcion = new MailAdopcion(publicacionSabatoDaEnAdopcionAPupi, sabato);
    assertEquals(mensaje, mailAdopcion.generarMail().getMensaje());
  }

  @Test
  void mailDeAdopcionAsuntoValido() {
    String asunto ="¡Tenemos noticias!. Conseguimos adoptante para: feli";
    mailAdopcion = new MailAdopcion(publicacionSabatoDaEnAdopcionAPupi, sabato);
    assertEquals(asunto, mailAdopcion.generarMail().getAsunto());
  }
  
  @Test
  void mailDeAdopcionDestinatarioValido() {
    String destinatario ="fedebal@gmail.com";
    mailAdopcion = new MailAdopcion(publicacionSabatoDaEnAdopcionAPupi, sabato);
    assertEquals(destinatario, mailAdopcion.generarMail().getDestinatario());
  }  

}
