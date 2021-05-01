package dominio.usuarios;

import java.util.List;
import dominio.personas.*;

public class Administrador extends UsuarioBase{
  void agregarUnaCaracteristica(Caracteristicas caracteristicas, String titulo, List<String> opciones){
    Caracteristica caracteristica = new Caracteristica(titulo, opciones);
    caracteristicas.agregarCaracteristica(caracteristica);
  }
  void eliminarUnaCaracteristica(Caracteristicas caracteristicas, String titulo){
    caracteristicas.borrarCaracteristica(titulo);
  }

  public Administrador() {
  }
}
