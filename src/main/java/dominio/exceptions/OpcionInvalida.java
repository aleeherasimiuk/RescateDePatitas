package dominio.exceptions;

public class OpcionInvalida extends RuntimeException {
  public OpcionInvalida(String caracteristica){
    super("La opcion ingresada no forma parte de las opciones de la caracteristica "+ caracteristica.toUpperCase());
  }
}
