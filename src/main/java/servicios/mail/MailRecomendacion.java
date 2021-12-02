package servicios.mail;

import java.util.List;

import dominio.adopcion.DarEnAdopcion;
import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class MailRecomendacion extends Mailer {

  private final Duenio adoptante;
  private final List<DarEnAdopcion> publicaciones;

  public MailRecomendacion(Duenio adoptante, List<DarEnAdopcion> publicaciones) {
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
    stringBuilder.append("Hola " + adoptante.getDatosPersona().getContacto().getNombre() + "!<br>")
        .append("Sabemos que estás buscando adoptar a una mascota<br>")
        .append("Por eso te traemos las que mejor se adaptan a tus comodidades y preferencias<br>");

    for (DarEnAdopcion publicacion : publicaciones) {

      Mascota mascota = publicacion.getMascota();
      stringBuilder.append("Nombre: ").append(mascota.getNombre()).append("<br>").append("Edad: ")
          .append(mascota.getEdad()).append(" años<br>");

      stringBuilder.append("Caracteristicas: <br>");

      List<String> caracteristicas = mascota.getCaracteristicas();

      caracteristicas.forEach(caracteristica -> {
        stringBuilder.append('-').append(caracteristica).append("<br>");
      });

      if(!mascota.getFotos().isEmpty()){
        stringBuilder.append("<br><img width=300 src=" + mascota.getFotoPerfil() + ">");
      }
      stringBuilder.append("<br><br><br>");

    }

    return stringBuilder.toString();
  }

  @Override
  protected String asunto() {
    return "¡Hey!. ¡Tenemos algunas recomendaciones para vos!";
  }

}
