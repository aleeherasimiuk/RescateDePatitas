package dominio.exceptions;

public class CaracteristicaInvalida extends RuntimeException{
  public CaracteristicaInvalida() {
    super("La caracteristica ingresada no existe");
  }
}

