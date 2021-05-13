package dominio.exceptions;

public class FileNotFound extends RuntimeException{
  public FileNotFound(){
    super("¡Lo sentimos! Por un error interno no se pudo verificar la seguridad de su contraseña.");
  }
}
