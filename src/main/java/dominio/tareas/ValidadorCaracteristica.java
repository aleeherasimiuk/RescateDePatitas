package dominio.tareas;

import dominio.exceptions.CaracteristicaInvalida;
import dominio.exceptions.OpcionInvalida;
import dominio.mascota.Caracteristica;
import dominio.repositorio.RepositorioCaracteristicas;

public class ValidadorCaracteristica {

  public void validarCaracteristica(String caracteristica, String valor) {
    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getINSTANCE();
    Caracteristica caracteristica_obtenida = repositorioCaracteristicas.obtenerCaracteristica(caracteristica.toUpperCase());
    if(caracteristica_obtenida==null) {
      throw new CaracteristicaInvalida();
    }
    if (!caracteristica_obtenida.tieneEstaOpcion(valor)){
      throw new OpcionInvalida(caracteristica.toUpperCase());
    }
  }
  
}
