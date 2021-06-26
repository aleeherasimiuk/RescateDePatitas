package servicios.mail;

import dominio.personas.Contacto;
import dominio.rescate.RescateConChapita;

public class MailerDuenio extends JavaMail<RescateConChapita>{

  @Override
  protected String destinatario(RescateConChapita rescate) {
    return rescate.datosDeContacto().getEmail();
  }

  @Override
  protected String mensaje(RescateConChapita rescate) {

    Contacto datosDeContacto = rescate.datosDeContacto();

    String mensaje = "";

    mensaje += "Hola! " + datosDeContacto.getNombre() + "\n";
    mensaje += "Estamos muy contentos de anunciarte que encontramos tu mascota!\n";
    mensaje += rescate.getMascota().getApodo() + " fue encontrada por " + datosDeContacto.getNombre();
    mensaje += "\n\n";
    mensaje += "Rescatista: " + datosDeContacto.getNombre() + " " + datosDeContacto.getApellido() + "\n";
    mensaje += "Teléfono: " + datosDeContacto.getTelefono() + "\n";
    mensaje += "Email " + datosDeContacto.getEmail() + "\n";
    mensaje += "Descripción en la que se encontró la mascota: " + rescate.getDescripcion();

    return mensaje;
  }

  @Override
  protected String asunto(RescateConChapita rescate) {
    return "Tenemos buenas noticias!. Encontramos a " + rescate.getMascota().getApodo();
  }
  
}
