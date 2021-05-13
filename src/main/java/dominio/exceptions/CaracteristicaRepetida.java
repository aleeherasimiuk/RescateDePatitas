package dominio.exceptions;

public class CaracteristicaRepetida extends RuntimeException{

  public CaracteristicaRepetida(){
    super("La característica ingresada ya existe");
  }
  
}
