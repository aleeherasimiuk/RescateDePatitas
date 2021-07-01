package servicios.mail;

import java.util.List;

import dominio.adopcion.DarEnAdopcion;
import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class MailRecomendacion extends Mailer{

  private final Duenio adoptante;
  private final List<DarEnAdopcion> publicaciones;

  public MailRecomendacion(Duenio adoptante, List<DarEnAdopcion> publicaciones){
    this.adoptante = adoptante;
    this.publicaciones = publicaciones;
  }

  @Override
  protected String destinatario() {
    return adoptante.getDatosPersona().getContacto().getEmail();
  }

  @Override
  protected String mensaje() {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Hola!\n")
      .append("Sabemos que estás buscando adoptar a una mascota\n")
      .append("Por eso te traemos las que mejor se adaptan a tus comodidades y preferencias\n\n");

    for (DarEnAdopcion publicacion : publicaciones) {

      Mascota mascota = publicacion.getMascota();
      stringBuilder.append(mascota.getNombre()).append('\n')
                   .append(mascota.getEdad()).append(" años\n\n");

      stringBuilder.append("Caracteristicas: \n");

      List<String> caracteristicas = mascota.getCaracteristicas();

      caracteristicas.forEach(caracteristica -> {
        stringBuilder.append('\t').append(caracteristica).append('\n');
      });

      stringBuilder.append("\n\n\n");
      
    }
    
    return stringBuilder.toString();
  }

  @Override
  protected String asunto() {
    return "¡Hey!. ¡Tenemos algunas recomendaciones para vos!";
  }
  
}
