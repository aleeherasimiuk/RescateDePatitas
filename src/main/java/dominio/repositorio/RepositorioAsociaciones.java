package dominio.repositorio;

import dominio.asociacion.Asociacion;
import dominio.asociacion.AsociacionComparator;
import dominio.exceptions.NoHayAsociacionesDisponibles;
import dominio.ubicacion.Coordenadas;

public class RepositorioAsociaciones extends Repositorio<Asociacion>{
  private static final RepositorioAsociaciones INSTANCE = new RepositorioAsociaciones();

  private RepositorioAsociaciones() {}

  public static RepositorioAsociaciones getInstance() {
    return INSTANCE;
  }

  public Asociacion obtenerLaMasCercana(Coordenadas ubicacionDelRescate){
    return repositorio.stream().min(new AsociacionComparator(ubicacionDelRescate)).orElseThrow(() -> new NoHayAsociacionesDisponibles());
  }

  
}
