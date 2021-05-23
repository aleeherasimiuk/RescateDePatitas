package dominio.repositorio;

import dominio.asociacion.Asociacion;
import dominio.asociacion.AsociacionComparator;
import dominio.exceptions.NoHayAsociacionesDisponibles;
import dominio.rescate.Rescate;

public class RepositorioAsociaciones extends Repositorio<Asociacion>{
  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  private RepositorioAsociaciones() {}

  public static RepositorioAsociaciones getInstance() {
    return INSTANCE;
  }

  public Asociacion obtenerLaMasCercana(Rescate rescate){
    return repositorio.stream().min(new AsociacionComparator(rescate)).orElseThrow(() -> new NoHayAsociacionesDisponibles());
  }
}
