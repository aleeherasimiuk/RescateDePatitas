package dominio.repositorio;

import dominio.mascota.Mascota;

public class RepositorioMascotas extends Repositorio<Mascota> {
  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  private RepositorioMascotas() {}

  public static RepositorioMascotas getINSTANCE() {
    return INSTANCE;
  }

  @Override
  protected Class<Mascota> getClassName() {
    return Mascota.class;
  }
}
