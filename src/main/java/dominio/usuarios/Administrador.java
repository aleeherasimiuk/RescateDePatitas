package dominio.usuarios;

import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;

public class Administrador extends Usuario{

  public Administrador(String username, String password) {
    super(username, password);
  }

  public void agregarUnaCaracteristica(String titulo, String... opciones) {
    Caracteristica caracteristica = new Caracteristica(titulo);

    for (String opcion : opciones) {
      caracteristica.agregarOpcion(opcion);
    }

    RepositorioCaracteristicas.getINSTANCE().registrar(caracteristica);
  }

  public void eliminarUnaCaracteristica(String titulo) {
    RepositorioCaracteristicas.getINSTANCE().borrarCaracteristica(titulo);
  }
}