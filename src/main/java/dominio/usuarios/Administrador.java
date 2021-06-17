package dominio.usuarios;

import dominio.repositorio.RepositorioCaracteristicas;

public class Administrador extends Usuario{

  public Administrador(String username, String password) {
    super(username, password);
  }

  public void agregarUnaCaracteristica(String caracteristica) {
    RepositorioCaracteristicas.getINSTANCE().registrar(caracteristica);
  }

  public void eliminarUnaCaracteristica(String titulo) {
    RepositorioCaracteristicas.getINSTANCE().borrarCaracteristica(titulo);
  }
}