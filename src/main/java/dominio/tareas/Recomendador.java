package dominio.tareas;

import dominio.repositorio.RepositorioSolicitudesAdopcion;

public class Recomendador {

  public void enviarRecomendaciones(){

    RepositorioSolicitudesAdopcion repo = RepositorioSolicitudesAdopcion.getInstance();
    
    Matcher matcher = new Matcher();

    repo.forEach((solicitud) -> solicitud.recomendar(matcher.recomendaciones(solicitud)));
  
  }
  
}
