package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.rescate.RescateConChapita;
import dominio.util.Lista;

public class RepositorioRescates extends Repositorio<RescateConChapita>{
  private static final RepositorioRescates INSTANCE = new RepositorioRescates();

  private RepositorioRescates() {}

  public Lista<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return super.filtrar(RescateConChapita::sucedioDentroDeLosUltimos10Dias).map(RescateConChapita::getMascota);
  }

  public static RepositorioRescates getINSTANCE(){
    return INSTANCE;
  }
}
