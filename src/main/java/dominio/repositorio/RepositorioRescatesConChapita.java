package dominio.repositorio;

import java.util.List;
import java.util.stream.Collectors;

import dominio.mascota.Mascota;
import dominio.rescate.RescateConChapita;

public class RepositorioRescatesConChapita extends Repositorio<RescateConChapita>{
  private static final RepositorioRescatesConChapita INSTANCE = new RepositorioRescatesConChapita();

  private RepositorioRescatesConChapita() {}

  public List<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return super
      .filtrar(RescateConChapita::sucedioDentroDeLosUltimos10Dias)
      .stream()
      .map(RescateConChapita::getMascota)
      .collect(Collectors.toList());
  }

  public static RepositorioRescatesConChapita getINSTANCE(){
    return INSTANCE;
  }
}
