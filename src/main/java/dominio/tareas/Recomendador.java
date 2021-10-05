package dominio.tareas;

import dominio.repositorio.RepositorioSolicitudesAdopcion;
import servicios.mail.JavaMail;

public class Recomendador {

  public void enviarRecomendaciones(JavaMail javaMail){

    RepositorioSolicitudesAdopcion solicitudesAdopcion 
      = RepositorioSolicitudesAdopcion.getInstance();

    solicitudesAdopcion.todos()
      .forEach((solicitud) -> solicitud.recomendar(solicitud.recomendaciones(), javaMail));
  
  }
  
}
