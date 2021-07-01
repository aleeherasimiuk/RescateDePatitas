import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.mascota.Mascota;
import dominio.repositorio.RepositorioDuenios;
import dominio.rescate.RescateConChapita;
import servicios.mail.MailRescateConChapita;

class MailerTest {
  Fixture fixture = new Fixture();
  
  Mascota pupi;
  RescateConChapita rescateConChapita;
  MailRescateConChapita mailRescateConChapita;
  
  @BeforeEach
  void setup() {
    pupi = fixture.getPupi();
    rescateConChapita = fixture.getRescatePupi();
    
    mailRescateConChapita = new MailRescateConChapita(rescateConChapita);    
  }
  
  @Test
  void mailRescatePupi() {
    String mensaje = "";
    mensaje += "";
    
    System.out.println(mailRescateConChapita.generarMail().getMensaje());
  }

}
