package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.rescate.Rescate;
import dominio.util.Lista;

public class RepositorioRescates extends Repositorio<Rescate>{
  private static final RepositorioRescates INSTANCE = new RepositorioRescates();

  private RepositorioRescates() {}

  public Lista<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return super.filtrar(Rescate::sucedioDentroDeLosUltimos10Dias).map(Rescate::getMascota);
  }

  public static RepositorioRescates getINSTANCE(){
    return INSTANCE;
  } 
  
}
