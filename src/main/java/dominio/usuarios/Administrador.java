package dominio.usuarios;

import dominio.caracteristicas.Caracteristica;
import dominio.caracteristicas.Caracteristicas;

public class Administrador {

  private Usuario usuario;

  public Administrador(String username, String password) {
    this.usuario = new Usuario(username, password);
  }

  public void agregarUnaCaracteristica(Caracteristicas caracteristicas, String titulo, String... opciones) {
    Caracteristica caracteristica = new Caracteristica(titulo);

    for (String opcion : opciones) {
      caracteristica.agregarOpcion(opcion);
    }

    caracteristicas.agregarCaracteristica(caracteristica);
  }

  public void eliminarUnaCaracteristica(Caracteristicas caracteristicas, String titulo) {

    caracteristicas.borrarCaracteristica(titulo);
  }

  public String getNombreDeUsuario() {
    return usuario.getNombreUsuario();
  }

}