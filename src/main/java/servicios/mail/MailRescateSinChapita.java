package servicios.mail;

import dominio.personas.Contacto;
import dominio.rescate.RescateSinChapita;

public class MailRescateSinChapita extends Mailer{

  private final RescateSinChapita rescate;

  public MailRescateSinChapita(RescateSinChapita rescate) {
    this.rescate = rescate;
  }

  @Override
  protected String destinatario() {
    return rescate.emailDeContacto();
  }

  @Override
  protected String mensaje() {
    Contacto datosDeContacto = rescate.getContacto();

    String mensaje = "";

    mensaje += "Hola! " + datosDeContacto.getNombre() + "\n";
    mensaje += "Estamos muy contentos de anunciarte que encontramos al dueño de una mascota que encontraste";
    mensaje += "\n\n";
    mensaje += datosDeContacto.getNombre() + " " + datosDeContacto.getApellido();
    mensaje += " " + "se contactará con vos para continuar con el proceso";

    return mensaje;
  }

  @Override
  protected String asunto() {
    return "Buenas noticias!. Identificamos al dueño de una mascota que encontraste";
  }

}
