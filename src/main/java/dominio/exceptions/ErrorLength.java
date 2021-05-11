package dominio.exceptions;

public class ErrorLength extends RuntimeException{
  public ErrorLength (){
    super("La contraseña debe contener como minimo 8 caracteres.");
  }
}
