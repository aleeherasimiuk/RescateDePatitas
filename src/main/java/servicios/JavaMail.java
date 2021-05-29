package servicios;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public abstract class JavaMail <T> {

  private final Session session;
  private String emisor;
  private String password;

  public JavaMail() {

    credenciales();
  
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

  private void credenciales(){
    Properties credenciales = new Properties();
    FileReader archivo_credenciales;
    try {
      archivo_credenciales = new FileReader("keys.properties");
      credenciales.load(archivo_credenciales);
      password = credenciales.getProperty("password");
      emisor = credenciales.getProperty("mail");
      archivo_credenciales.close();
      
    } catch (IOException e) {
      throw new CredencialesException();
    }

  }

  protected abstract String destinatario(T t);
  protected abstract String mensaje(T t);
  protected abstract String asunto(T t);


  public void enviarMail(T t){
    MimeMessage message = new MimeMessage(session);
    try {
      // Quien envia el correo
      message.setFrom(new InternetAddress(emisor));

      // A quien va dirigido
      //message.addRecipient(Message.RecipientType.TO, new InternetAddress(contacto.getEmail()));

      message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario(t)));
      message.setSubject(asunto(t));
      message.setText(mensaje(t));

      Transport transport = session.getTransport("smtp");
      transport.connect(emisor, password);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (MessagingException e) {
      throw new EmailException();
    }
    
  }
  
}
