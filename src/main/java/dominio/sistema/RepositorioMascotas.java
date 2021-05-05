package dominio.sistema;

import dominio.mascota.Clase;
import dominio.mascota.Mascota;
import dominio.mascota.Sexo;
import dominio.util.Lista;

import javax.crypto.MacSpi;
import java.util.List;


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
