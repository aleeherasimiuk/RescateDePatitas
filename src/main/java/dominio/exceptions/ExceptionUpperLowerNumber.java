package dominio.exceptions;

public class ExceptionUpperLowerNumber extends RuntimeException{
  public ExceptionUpperLowerNumber(){
    super("La contraseña debe contener como minimo: una minuscula, una mayuscula y un numero");
  }
}
