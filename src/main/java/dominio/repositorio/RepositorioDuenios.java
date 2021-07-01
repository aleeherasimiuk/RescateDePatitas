package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class RepositorioDuenios extends Repositorio<Duenio>{
  private static final RepositorioDuenios INSTANCE = new RepositorioDuenios();

  private RepositorioDuenios() {}

  public Duenio duenioDe(Mascota mascota){
    return super.buscar(duenio -> duenio.esMiMascota(mascota));
  }

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }
}
