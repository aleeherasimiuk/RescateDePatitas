package dominio.exceptions;

public class ErrorIO extends RuntimeException{
  public ErrorIO(){
    super("¡Lo sentimos! Por un error interno no se pudo verificar la seguridad de su contraseña.");
  }
}
