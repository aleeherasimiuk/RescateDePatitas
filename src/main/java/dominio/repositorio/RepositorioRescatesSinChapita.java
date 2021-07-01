package dominio.repositorio;

import dominio.rescate.RescateSinChapita;
import dominio.util.Lista;

public class RepositorioRescatesSinChapita extends Repositorio<RescateSinChapita>{
  private static final RepositorioRescatesSinChapita INSTANCE = new RepositorioRescatesSinChapita();

  private RepositorioRescatesSinChapita() {}

  public Lista<RescateSinChapita> publicacionesAprobadas(){
    return repositorio.filter(RescateSinChapita::estaAprobada);
  } 

  public Lista<RescateSinChapita> publicacionesPendientes(){
    return repositorio.filter(RescateSinChapita::estaPendiente);
  }

  public static RepositorioRescatesSinChapita getINSTANCE(){
    return INSTANCE;
  } 
}
