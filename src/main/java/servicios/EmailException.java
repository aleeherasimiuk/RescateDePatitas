package servicios;

public class EmailException extends RuntimeException{
  
  public EmailException(){
    super("No se ha podido enviar el email.");
  }
}
