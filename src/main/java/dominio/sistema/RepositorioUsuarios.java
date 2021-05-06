package dominio.sistema;

import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;
import dominio.util.Lista;

public class RepositorioUsuarios {
  private Lista<Duenio> repositorio;
  private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

  private RepositorioUsuarios() {
    this.repositorio = new Lista<>();
  }

  public void registrarDuenio(Duenio duenio){
    repositorio.add(duenio);
  }

  public Duenio duenioDe(Mascota mascota){
    return repositorio.find(duenio -> duenio.esMiMascota(mascota));
  }

  public void eliminarDuenio(Duenio duenio){
    repositorio.remove(duenio);
  }

  public static RepositorioUsuarios getINSTANCE() {
    return INSTANCE;
  }
}
