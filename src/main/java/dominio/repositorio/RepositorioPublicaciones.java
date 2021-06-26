package dominio.repositorio;

import dominio.rescate.RescateSinChapita;
import dominio.util.Lista;

public class RepositorioPublicaciones extends Repositorio<RescateSinChapita>{
  private static final RepositorioPublicaciones INSTANCE = new RepositorioPublicaciones();

  private RepositorioPublicaciones() {}

  public Lista<RescateSinChapita> publicacionesAprobadas(){
    return repositorio.filter(RescateSinChapita::estaAprobada);
  } 

  public Lista<RescateSinChapita> publicacionesPendientes(){
    return repositorio.filter(RescateSinChapita::estaPendiente);
  }

  public static RepositorioPublicaciones getINSTANCE(){
    return INSTANCE;
  } 
}
