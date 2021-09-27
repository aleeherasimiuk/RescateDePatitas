package dominio.repositorio;

import dominio.mascota.Mascota;
import dominio.usuarios.Duenio;

public class RepositorioDuenios extends Repositorio<Duenio>{
  private static final RepositorioDuenios INSTANCE = new RepositorioDuenios();

  private RepositorioDuenios() {}

  public Duenio duenioDe(Mascota mascota){
    return paraTodos(list -> list.stream().filter(duenio -> duenio.esMiMascota(mascota)).findFirst().orElse(null));
  }

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }
}
