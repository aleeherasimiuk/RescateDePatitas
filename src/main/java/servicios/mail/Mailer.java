package servicios.mail;

public abstract class Mailer {

  public Mail generarMail(){

    final String destinatario = destinatario();
    final String mensaje      = mensaje();
    final String asunto       = asunto();

    return new Mail(destinatario, asunto, mensaje);
  }

  protected abstract String destinatario();
  protected abstract String mensaje();
  protected abstract String asunto();
  
}
