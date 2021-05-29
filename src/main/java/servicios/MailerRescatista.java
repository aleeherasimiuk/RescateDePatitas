package servicios;

import dominio.mascota.Mascota;
import dominio.personas.Contacto;
import dominio.rescate.Publicacion;

public class MailerRescatista extends JavaMail<Publicacion>{

  @Override
  protected String destinatario(Publicacion publicacion) {
    return publicacion.emailDeContacto();
  }

  @Override
  protected String mensaje(Publicacion publicacion) {
    Contacto datosDeContacto = publicacion.getRescate().datosDeContacto();
    Mascota mascota = publicacion.getRescate().getMascota();

    String mensaje = "";

    mensaje += "Hola! " + datosDeContacto.getNombre() + "\n";
    mensaje += "Estamos muy contentos de anunciarte que encontramos al dueño de " + mascota.getApodo();
    mensaje += "\n\n";
    mensaje += datosDeContacto.getNombre() + " " + datosDeContacto.getApellido();
    mensaje += " " + "se contactará con vos para continuar con el proceso";

    return mensaje;
  }

  @Override
  protected String asunto(Publicacion publicacion) {
    return "Buenas noticias!. Identificamos al dueño de " + publicacion.getRescate().getMascota().getApodo();
  }
  
}
