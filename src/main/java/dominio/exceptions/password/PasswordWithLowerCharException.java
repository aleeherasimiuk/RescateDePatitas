package dominio.exceptions.password;

public class PasswordWithLowerCharException extends RuntimeException{
  public PasswordWithLowerCharException(){
    super("La contraseña debe contener como minimo una letra minúscula");
  }
}
