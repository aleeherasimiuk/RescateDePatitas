package servicios.mail;

public class Mail {

  private final String destinatario;
  private final String asunto;
  private final String mensaje;
  
  public Mail(String destinatario, String asunto, String mensaje) {
    this.destinatario = destinatario;
    this.asunto = asunto;
    this.mensaje = mensaje;
  }

  public String getDestinatario() {
    return destinatario;
  }

  public String getAsunto() {
    return asunto;
  }

  public String getMensaje() {
    return mensaje;
  }
  
  
}
