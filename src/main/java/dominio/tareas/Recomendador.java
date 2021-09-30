package dominio.tareas;

import dominio.repositorio.RepositorioSolicitudesAdopcion;
import servicios.mail.JavaMail;

public class Recomendador {

  public void enviarRecomendaciones(JavaMail javaMail){

    RepositorioSolicitudesAdopcion solicitudesAdopcion 
      = RepositorioSolicitudesAdopcion.getInstance();

    solicitudesAdopcion
      .forEach((solicitud) -> solicitud.recomendar(solicitud.recomendaciones(), javaMail));
  
  }
  
}
