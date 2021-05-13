package dominio.exceptions.password;

public class CommonPasswordException extends RuntimeException{
  public CommonPasswordException(){
    super("Contraseña vulnerable, elegir otra, por favor.");
  }
}
