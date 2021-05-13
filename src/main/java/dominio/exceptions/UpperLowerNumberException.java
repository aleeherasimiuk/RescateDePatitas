package dominio.exceptions;

public class UpperLowerNumberException extends RuntimeException{
  public UpperLowerNumberException(){
    super("La contraseña debe contener como minimo: una minuscula, una mayuscula y un numero");
  }
}
