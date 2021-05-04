package dominio.usuarios;

import dominio.caracteristicas.Caracteristica;
import dominio.caracteristicas.Caracteristicas;

public class Administrador extends Usuario {

  public void agregarUnaCaracteristica(Caracteristicas caracteristicas, String titulo, String... opciones){
    Caracteristica caracteristica = new Caracteristica(titulo);

    for (String opcion : opciones) {
      caracteristica.agregarOpcion(opcion);
    }

    caracteristicas.agregarCaracteristica(caracteristica);
  }

  public void eliminarUnaCaracteristica(Caracteristicas caracteristicas, String titulo){
    //caracteristicas.borrarCaracteristica(titulo);
  }

  public Administrador(String nombreUsuario, String contrasenia) {
    super(nombreUsuario, contrasenia);
    // TODO Auto-generated constructor stub

  }

  
}