package dominio.exceptions.password;

public class PasswordWithNumberCharException extends RuntimeException{
  public PasswordWithNumberCharException(){
    super("La contraseña debe contener como minimo un número");
  }
}
