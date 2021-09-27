package dominio.repositorio;

import java.util.List;

import dominio.rescate.RescateSinChapita;

public class RepositorioRescatesSinChapita extends Repositorio<RescateSinChapita>{
  private static final RepositorioRescatesSinChapita INSTANCE = new RepositorioRescatesSinChapita();

  private RepositorioRescatesSinChapita() {}

  public List<RescateSinChapita> publicacionesAprobadas(){
    return filtrar(RescateSinChapita::estaAprobada);
  } 

  public List<RescateSinChapita> publicacionesPendientes(){
    return filtrar(RescateSinChapita::estaPendiente);
  }

  public static RepositorioRescatesSinChapita getINSTANCE(){
    return INSTANCE;
  } 
}
