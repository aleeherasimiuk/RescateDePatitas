import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioDuenios;
import dominio.rescate.RescateConChapita;
import servicios.mail.MailRescateConChapita;

class MailerTest {
  Fixture fixture = new Fixture();
  
  RescateConChapita rescateConChapita;
  MailRescateConChapita mailRescateConChapita;
  
  @BeforeEach
  void setup() {
    rescateConChapita = fixture.getRescatePupi();
    
    mailRescateConChapita = new MailRescateConChapita(rescateConChapita);    
  }
  
  @Test
  void mailRescatePupi() {

    String mensaje = "Hola! Federico\nEstamos muy contentos de anunciarte que encontramos tu mascota!\nPupi fue encontrada por Roberto\n\nRescatista: Roberto Gimenez\nTeléfono: 1180700543\nEmail robertito@gmail.com\nDescripción en la que se encontró la mascota: parece ser un gato siames";
    assertEquals(mensaje, mailRescateConChapita.generarMail().getMensaje());
  }

}
