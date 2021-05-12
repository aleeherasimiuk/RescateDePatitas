package dominio.exceptions;

public class IOException extends RuntimeException{
  public IOException(){
    super("¡Lo sentimos! Por un error interno no se pudo verificar la seguridad de su contraseña.");
  }
}
