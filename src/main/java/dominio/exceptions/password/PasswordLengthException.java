package dominio.exceptions.password;

public class PasswordLengthException extends RuntimeException{
  public PasswordLengthException(){
    super("La contraseña debe contener como minimo 8 caracteres.");
  }
}
