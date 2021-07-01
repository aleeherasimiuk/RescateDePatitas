package dominio.tareas;

import dominio.repositorio.RepositorioSolicitudesAdopcion;
import servicios.mail.JavaMail;

public class Recomendador {

  public void enviarRecomendaciones(JavaMail javaMail){

    RepositorioSolicitudesAdopcion repo = RepositorioSolicitudesAdopcion.getInstance();
    
    Matcher matcher = new Matcher();

    repo.forEach((solicitud) -> solicitud.recomendar(matcher.recomendaciones(solicitud), javaMail));
  
  }
  
}
