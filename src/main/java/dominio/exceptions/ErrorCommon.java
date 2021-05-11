package dominio.exceptions;

public class ErrorCommon extends RuntimeException{
  public ErrorCommon(){
    super("Contraseña vulnerable, elegir otra, por favor.");
  }
}
