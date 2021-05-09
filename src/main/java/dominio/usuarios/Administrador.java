package dominio.usuarios;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;

public class Administrador {

  private Usuario usuario;

  public Administrador(String username, String password) {
    this.usuario = new Usuario(username, password);
  }

  public void agregarUnaCaracteristica(String titulo, String... opciones) {
    Caracteristica caracteristica = new Caracteristica(titulo.toUpperCase());

    for (String opcion : opciones) {
      caracteristica.agregarOpcion(opcion.toUpperCase());
    }

    RepositorioCaracteristicas.getINSTANCE().agregarCaracteristica(caracteristica);
  }

  public void eliminarUnaCaracteristica(RepositorioCaracteristicas repositorioCaracteristicas, String titulo) {

    repositorioCaracteristicas.borrarCaracteristica(titulo);
  }

  public String getUsername() {
    return usuario.getUsername();
  }
}