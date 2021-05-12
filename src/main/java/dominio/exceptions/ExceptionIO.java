package dominio.exceptions;

public class ExceptionIO extends RuntimeException{
  public ExceptionIO(){
    super("¡Lo sentimos! Por un error interno no se pudo verificar la seguridad de su contraseña.");
  }
}
