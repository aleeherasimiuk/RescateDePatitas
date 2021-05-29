package servicios;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

  private final Session session;
  private final String emisor = "";
  private final String password = "";

  public JavaMail() {
  
    Properties props = new Properties();

    // Nombre del host de correo, es smtp.gmail.com
    props.setProperty("mail.smtp.host", "smtp.gmail.com");

    // TLS si está disponible
    props.setProperty("mail.smtp.starttls.enable", "true");

    // Puerto de gmail para envio de correos
    props.setProperty("mail.smtp.port","587");

    // Nombre del usuario
    props.setProperty("mail.smtp.user", emisor);

    // Si requiere o no usuario y password para conectarse.
    props.setProperty("mail.smtp.auth", "true");

    session = Session.getDefaultInstance(props);
    session.setDebug(true);
  
  }


  public void enviarMail(String mensaje, String destino) throws AddressException, MessagingException{
    MimeMessage message = new MimeMessage(session);
    // Quien envia el correo
    message.setFrom(new InternetAddress(emisor));

    // A quien va dirigido
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));

    message.setSubject("Rescate de patitas informa que se ha identificado a la mascota.");
    message.setText("Mensaje de prueba");

    Transport t = session.getTransport("smtp");
    t.connect(emisor,password);
    t.sendMessage(message,message.getAllRecipients());
    t.close();
  }

  
  
}
