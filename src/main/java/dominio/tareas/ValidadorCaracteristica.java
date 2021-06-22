package dominio.tareas;

import dominio.exceptions.CaracteristicaInvalida;
import dominio.repositorio.RepositorioCaracteristicas;

public class ValidadorCaracteristica {

  public void validarCaracteristica(String caracteristica) {
    RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getINSTANCE();
    if(!repositorioCaracteristicas.existeCaracteristica(caracteristica)) {
      throw new CaracteristicaInvalida();
    }
  }

}
