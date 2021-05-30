package servicios;

public class CredencialesException extends RuntimeException{

  public CredencialesException() {
    super("No se han podido obtener las credenciales de envío de email");
  }
  
}
