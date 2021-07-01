package servicios.mail;

import dominio.adopcion.DarEnAdopcion;
import dominio.personas.Contacto;
import dominio.usuarios.Duenio;

public class MailAdopcion extends Mailer{

  private final DarEnAdopcion adopcion;
  private final Duenio adoptante;

  public MailAdopcion(DarEnAdopcion adopcion, Duenio adoptante) {
    this.adopcion = adopcion;
    this.adoptante = adoptante;
  }

  @Override
  protected String destinatario() {
    return adopcion.getDuenio().getDatosPersona().getContacto().getEmail();
  }

  @Override
  protected String mensaje() {
    
    Contacto contacto = adoptante.getDatosPersona().getContacto();
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Hemos encontrado un adpotante para tu mascota\n")
    .append(adoptante.getDatosPersona().getNombre()).append(" desea adoptar a tu mascota")
    .append("Puedes contactarte mediante los siguientes medios: \n")
    .append("Telefono: ").append(contacto.getTelefono()).append('\n')
    .append("Email: ").append(contacto.getEmail()).append('\n');

    return stringBuilder.toString();
  }

  @Override
  protected String asunto() {
    return "¡Tenemos noticias!. Conseguimos adoptante para: " + adopcion.getMascota().getApodo();
  }
  
}
