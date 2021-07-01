package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.rescate.RescateConChapita;
import dominio.util.Lista;

public class RepositorioRescatesConChapita extends Repositorio<RescateConChapita>{
  private static final RepositorioRescatesConChapita INSTANCE = new RepositorioRescatesConChapita();

  private RepositorioRescatesConChapita() {}

  public Lista<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return super.filtrar(RescateConChapita::sucedioDentroDeLosUltimos10Dias).map(RescateConChapita::getMascota);
  }

  public static RepositorioRescatesConChapita getINSTANCE(){
    return INSTANCE;
  }
}
