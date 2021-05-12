package dominio.exceptions;

public class ExceptionLength extends RuntimeException{
  public ExceptionLength(){
    super("La contraseña debe contener como minimo 8 caracteres.");
  }
}
