package dominio.usuarios;

import dominio.repositorio.RepositorioCaracteristicas;

public class Admin extends Usuario{

  public Admin(String username, String password) {
    super(username, password);
  }

  public void agregarUnaCaracteristica(String caracteristica) {
    RepositorioCaracteristicas.getINSTANCE().registrar(caracteristica.toUpperCase());
  }

  public void eliminarUnaCaracteristica(String titulo) {
    RepositorioCaracteristicas.getINSTANCE().borrarCaracteristica(titulo);
  }

  public void agregarCaracteristicas(String... caracteristicas){
    for (String caracteristica : caracteristicas) {
      this.agregarUnaCaracteristica(caracteristica);
    }
  }
}