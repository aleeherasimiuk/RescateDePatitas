package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.rescate.Rescate;
import dominio.util.Lista;

import java.util.List;

public class RepositorioRescates {
  private Lista<Rescate> repositorio;
  private static final RepositorioRescates INSTANCE = new RepositorioRescates();


  private RepositorioRescates() {
    this.repositorio = new Lista<>();
  }

  public void registrarRescate(Rescate rescate){
    repositorio.add(rescate);
  }

  public Lista<Mascota> mascotasEncontradasEnLosUltimos10Dias() {
    return repositorio.filter(Rescate::sucedioDentroDeLosUltimos10Dias).map(Rescate::getMascota);
  }

  public static RepositorioRescates getINSTANCE() {
    return INSTANCE;
  }
}
