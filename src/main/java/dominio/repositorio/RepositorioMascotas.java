package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.util.Lista;



public class RepositorioMascotas {
  private Lista<Mascota> repositorio;
  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  private RepositorioMascotas() {
    this.repositorio = new Lista<>();
  }

  public void registrarMascota(Mascota mascota){
    repositorio.add(mascota);
  }

  public int cantDeMascotasRegistradas() {
    return repositorio.size();
  }

  public void eliminarMascota(Mascota mascota){
    repositorio.remove(mascota);
  }

  public static RepositorioMascotas getINSTANCE() {
    return INSTANCE;
  }
}
