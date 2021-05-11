package dominio.exceptions;

public class ErrorUpperLowerNumber extends RuntimeException{
  public ErrorUpperLowerNumber(){
    super("La contraseña debe contener como minimo: una minuscula, una mayuscula y un numero");
  }
}
