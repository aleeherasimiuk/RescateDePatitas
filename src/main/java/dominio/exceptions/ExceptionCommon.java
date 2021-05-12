package dominio.exceptions;

public class ExceptionCommon extends RuntimeException{
  public ExceptionCommon(){
    super("Contraseña vulnerable, elegir otra, por favor.");
  }
}
